package transfer.thread;

import javax.swing.*;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAcceptThread extends Thread{
    private ServerSocket serverSocket;
    public ServerAcceptThread(ServerSocket serverSocket){
        this.serverSocket=serverSocket;

    }
    @Override
    public void run(){
        try {
            while (true) {
                Socket socket=serverSocket.accept();
                //-------------状态信息流
                DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
                int state=JOptionPane.showConfirmDialog(null,"是否接收来自："+socket.getInetAddress().getHostAddress()+" 的文件？","Tips：",JOptionPane.OK_CANCEL_OPTION);

                if(state==JOptionPane.OK_OPTION) {
                    dataOutputStream.writeInt(1);
                    //启动接收线程
                    new FileAcceptThread(socket).start();
                }
                else
                    dataOutputStream.writeInt(0);

                //dataOutputStream.close();//---调用close关闭流会导致socket关闭
                //-------------状态信息流

            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "接收失败");
            this.run();
        }

    }

}
