import java.io.*;
import java.net.*;

public class SocketServer {
   // 为这个Socket选择一个端口8080:
   public static final int PORT = 8080;
   public static void main(String[] args) throws IOException {
       ServerSocket s = new ServerSocket(PORT);
       System.out.println("开始: " + s);
       try {
           // 生成一个Socket等待连接请求
           Socket socket = s.accept();
           try {
               System.out.println(
                       "接受连接请求： " + socket);
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
               while (true) {
                   String str = in.readLine();
                   if (str.equals("END")) {
                       break;
                   }
                   System.out.println("自客户端： " + str);
                   out.println(str);
               }
               // 关闭socket
           }
           finally {
               System.out.println("关闭...");
               socket.close();
           }
       }
       finally {
           s.close();
       }
   }
}