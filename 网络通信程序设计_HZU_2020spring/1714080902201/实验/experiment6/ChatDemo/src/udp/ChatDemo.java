package udp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
/**
 *
 *
 * 编写一个聊天的工具
 * 有收数据部分，和发数据部分
 * 这两个不部分同时执行
 * 就需要多线程技术
 * 一个线程控制收，一个线程控制发
 *
 * 因为收和发动作是不一致的，所以要定义两个方法
 * 而且这两个方法要封装到不同的类中
 *
 * @author 言曌
 * @date 2017/12/6 下午8:25
 */
class Send2 implements Runnable {
    private MulticastSocket socket;
    public Send2(MulticastSocket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            InetAddress group = InetAddress.getByName("228.5.6.8");
            socket.joinGroup(group);
            String line = null;
            while((line = reader.readLine())!= null) {
                byte[] buff = line.getBytes();
                DatagramPacket packet = new DatagramPacket(buff,buff.length,group,6789);
                socket.send(packet);
                if("bey".equals(line)) {
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("发送端失败");
        }
    }
}
class Rece2 implements Runnable {
    private  MulticastSocket socket;
    public Rece2(MulticastSocket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            byte[] buff = new byte[1024];
            InetAddress group = InetAddress.getByName("228.5.6.8");
            socket.joinGroup(group);
            while(true) {
                DatagramPacket packet = new DatagramPacket(buff,buff.length);
                socket.receive(packet);
                String data = new String(packet.getData(),0,packet.getLength());
                String ip = packet.getAddress().getHostAddress();
                if("bye".equals(data)) {
                    System.out.println(ip+"离开了");
                    continue;
                }
                System.out.println("ip:"+ip+" 说:"+data);
            }
        } catch (IOException e) {
            throw new RuntimeException("接受端失败");
        }
    }
}
public class ChatDemo {
    public static void main(String args[]) throws IOException {
        MulticastSocket sendSocket = new MulticastSocket();
        MulticastSocket receSocket = new MulticastSocket(6789);
        new Thread(new Send2(sendSocket)).start();
        new Thread(new Rece2(receSocket)).start();
    }
}