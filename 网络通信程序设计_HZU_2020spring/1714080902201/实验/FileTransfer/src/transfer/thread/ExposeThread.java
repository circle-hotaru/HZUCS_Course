package transfer.thread;

import transfer.utils.CommonUtils;
import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/*
暴露线程--持续3s一次
 */
public class ExposeThread extends Thread{
    private DatagramSocket socket;
    private InetAddress broadcastAddress;
    //暴露停止标记
    private boolean sign=true;
    public ExposeThread(DatagramSocket socket){
        this.socket=socket;
        broadcastAddress= CommonUtils.getBroadcastAddress();
        System.out.println(broadcastAddress.getHostAddress());
    }
    @Override
    public void run(){
        String tempData="expose";
        byte[] data;
        try {
            data = tempData.getBytes("UTF-8");
        /*
        线程无法重启！！！所以这辈子我也不把它给关了！！
         */
        while(true){
            if(sign) {
                //重新获取广播地址
                broadcastAddress = CommonUtils.getBroadcastAddress();
                //重新打包
                DatagramPacket packet = new DatagramPacket(data, 0, data.length, broadcastAddress, 54188);
                System.out.println(packet.getAddress().getHostAddress());
                socket.send(packet);

                //1s一次
                Thread.sleep(1000);
            }else{
                //1s一次
                Thread.sleep(1000);
                continue;
            }
        }
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"网络已切换！");
            //重新获取广播地址
            broadcastAddress=CommonUtils.getBroadcastAddress();
            //--test
            System.out.println(broadcastAddress.getHostAddress());
            //--test
            if(broadcastAddress!=null)
            this.run();
            else {
                JOptionPane.showConfirmDialog(null,"请连接网络后使用！");
                System.exit(0);
            }
        }
    }
    /*
    线程开关
     */
    public void setSwitch(boolean sign){
        this.sign=sign;
    }
}
