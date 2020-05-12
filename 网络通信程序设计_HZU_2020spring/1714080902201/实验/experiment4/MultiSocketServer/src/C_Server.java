import java.io.*;
import java.net.*;

public class C_Server extends Thread{
    private static Socket socket = null;
    public C_Server (Socket SS) {
        socket = SS;
    }
    @Override
    public void run() {
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter write = new PrintWriter(socket.getOutputStream(), true);
            while (true) {                
                String TmpString = read.readLine();
                if(TmpString.equalsIgnoreCase("END")){ break; }
                System.out.println("自客户端：" + TmpString);
                write.println("“" + TmpString + "”已收到！");
            }
            socket.close();
        } catch (IOException ex) {
        }
    }
}
