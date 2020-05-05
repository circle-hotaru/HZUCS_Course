import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) throws IOException {
          // 指定使用本地IP
          InetAddress addr =
                      InetAddress.getByName(null);
          System.out.println("addr = " + addr);
          Socket socket =
                      new Socket(addr, SocketServer.PORT);
          // 将代码放在Try语句中执行，以确保程序能关闭socket
          try {
                System.out.println("socket = " + socket);
                BufferedReader in =
                            new BufferedReader(
                            new InputStreamReader(
                            socket.getInputStream()));
                // 定义一个PrintWriter对象写输出流
                PrintWriter out =
                            new PrintWriter(
                            new BufferedWriter(
                            new OutputStreamWriter(
                            socket.getOutputStream())), true);
                for (int i = 0; i < 10; i++) {
                      out.println("测试 " + i);
                      String str = in.readLine();
                      System.out.println("自服务端： " + str);
                }
                out.println("END");
          }
          finally {
                System.out.println("关闭");
                socket.close();
          }
    }
}