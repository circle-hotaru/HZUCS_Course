package me.circlehotarux.UDPTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveMessage extends Thread{
    private DatagramSocket serverSocket;            //服务器套接字
    private DatagramPacket recvPacket;                  //接收报文
    private DatagramPacket sendPacket;                  //发送报文
    private byte[] buf = new byte[8096];           //8KB数组
    private S_Server parentUI;                      //消息窗口
    private String sendStr = "客户端你好，我是服务端";
    public ReceiveMessage(DatagramSocket socket, S_Server parentUI){
        serverSocket = socket;
        this.parentUI = parentUI;
    }
    @Override
    public void run(){
        while(true){
            try {
                recvPacket = new DatagramPacket(buf, buf.length);     //构建接收报文
                serverSocket.receive(recvPacket);                       //接收客户机数据
                String name = recvPacket.getAddress().toString();
                String s = new String(recvPacket.getData(), 0, recvPacket.getLength());
                parentUI.txtArea.append("\n来自主机：" + name + "\n端口：" + recvPacket.getPort() + "\n接收到的数据是：" + s );
                //回应客户端
                DatagramPacket sendPacket = new DatagramPacket( sendStr.getBytes(),sendStr.getBytes().length,recvPacket.getAddress(),recvPacket.getPort());
                serverSocket.send( sendPacket );
                //由于receivePacket在接收了数据之后，其内部消息长度值会变为实际接收消息的字节数
                //所以这里要将receivePacket的内部消息长度重新设置为1024
                recvPacket.setLength(8096);
            } catch (IOException ex) {
            }
        }
    }
}
