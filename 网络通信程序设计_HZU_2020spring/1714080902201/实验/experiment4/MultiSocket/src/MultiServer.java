import java.io.IOException;
import java.net.*;

public class MultiServer extends Thread{
    private static ServerSocket sServer = null;
    private Socket socket = null;
    public MultiServer(ServerSocket SS){
        sServer = SS;
    }
    public void run(){
        try {
            while (true) {                
                socket = sServer.accept();
                S_Server.ClientList.add(socket.getInetAddress().toString());
                new C_Server(socket).start();
            }
        } catch (IOException ex) {
        }
    }
}
