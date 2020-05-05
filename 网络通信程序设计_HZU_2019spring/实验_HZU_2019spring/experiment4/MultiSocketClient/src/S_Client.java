import java.io.*;
import java.net.*;
public class S_Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName(null), 8880);
        try {
            String userInput, TmpString;
            //创建一个接收键盘输入的输入流
            BufferedReader KeyIn = new BufferedReader(new InputStreamReader(System.in));
            //创建一个接收服务器输入的输入流
            BufferedReader recv = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //创建一个输出流
            PrintWriter send = new PrintWriter(socket.getOutputStream(), true);
            while (true) {                
                userInput=KeyIn.readLine();
                send.println(userInput);
                if(userInput.equalsIgnoreCase("END"))break;
                TmpString = recv.readLine();
                System.out.println("自服务端：" + TmpString);
            }
        } finally { socket.close(); }
    }
}