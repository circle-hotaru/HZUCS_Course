package me.circlehotarux;
import java.io.*;
import java.net.*;

public class UDPServerTest {
  static public void main(String args[]) {
    String sendStr = "客户端你好，我是服务端";
    try {
      DatagramSocket serverSocket = new DatagramSocket(3245);
      byte buf[] = new byte[8096];
      DatagramPacket recvPacket = new DatagramPacket(buf, buf.length);
      System.out.println("开始接受数据：");
      while (true) {
        serverSocket.receive(recvPacket);
        String name = recvPacket.getAddress().toString();
        System.out.println("\n来自主机：" + name + "\n端口："
                           + recvPacket.getPort());
        String recvStr = new String(recvPacket.getData(),0,recvPacket.getLength());
        System.out.println("接受到的数据是: " + recvStr);
        
        //回应客户端
        DatagramPacket sendPacket = new DatagramPacket( sendStr.getBytes(),sendStr.getBytes().length,recvPacket.getAddress(),recvPacket.getPort());
        serverSocket.send( sendPacket );
        //由于receivePacket在接收了数据之后，其内部消息长度值会变为实际接收消息的字节数
        //所以这里要将receivePacket的内部消息长度重新设置为1024
        recvPacket.setLength(8096);
      }
    }
    catch (SocketException e) {
      e.printStackTrace();
      System.exit(1);
    }
    catch (IOException e) {
      System.out.println("网络通讯出现错误，问题在" + e.toString());
    }
  }
}