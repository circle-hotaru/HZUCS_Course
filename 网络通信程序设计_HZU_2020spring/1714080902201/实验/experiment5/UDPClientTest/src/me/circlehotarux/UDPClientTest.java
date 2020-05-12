package me.circlehotarux;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClientTest {
   public static void main(String args[]) {
        Scanner txtName = new Scanner(System.in);
        Scanner txtinput = new Scanner(System.in);
        byte[] buf = new byte[8096];
        //获取服务端地址
        System.out.println("请输入服务器IP地址：");
        String serverName = txtName.nextLine();
        try {
           while (true) {
               System.out.println("服务器已连接！\n请输入发送内容：");
               //输入待发送的内容
               String sendStr = txtinput.nextLine();
               if(sendStr.equals("exit")){
                   System.out.println("连接已结束！");
                   break;
               }
                DatagramSocket clientSocket = new DatagramSocket(3456);
                DatagramPacket sendPacket = new DatagramPacket(sendStr.getBytes(), sendStr.getBytes().length,
                        InetAddress.getByName(serverName), 3245);
                clientSocket.send(sendPacket);
                System.out.println("客户端开始传送数据！");
                //接收回应
                DatagramPacket recvPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(recvPacket);
                System.out.println("客户端接收自服务端消息: ");
                String recvStr = new String( recvPacket.getData(), 0 ,recvPacket.getLength())+" from "+
                         recvPacket.getAddress().getHostAddress()+":"+recvPacket.getPort();
                 System.out.println(recvStr);
                 //由于recvPacket在接收了数据之后，其内部消息长度值会变为实际接收消息的字节数
                 //所以这里要将recvPacket的内部消息长度重新设置为1024                      
                 recvPacket.setLength(8096);           
                 clientSocket.close();
           }                     
       }catch (IOException ioe) {
           System.out.println(ioe.toString());
       }   
    }
}
