package transfer.utils;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.Enumeration;

public class CommonUtils {
    private static Toolkit toolkit=Toolkit.getDefaultToolkit();
    //广播地址
    public static InetAddress getBroadcastAddress(){
        byte[] address=getIPV4Address().getAddress();
        //将IP的最后一字节转为255（IPV4字节）
        address[3]=(byte)255;
        //hostAddress.
        //返回该IP对象
        try {
            return InetAddress.getByAddress(address);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    //获取本机网内地址
    public static InetAddress getIPV4Address(){
        try {
            //获取所有网络接口
            Enumeration<NetworkInterface> allNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
            //遍历所有网络接口
            for(;allNetworkInterfaces.hasMoreElements();){
                NetworkInterface networkInterface=allNetworkInterfaces.nextElement();
                //如果此网络接口为 回环接口 或者 虚拟接口(子接口) 或者 未启用 或者 描述中包含VM
                if(networkInterface.isLoopback()||networkInterface.isVirtual()||!networkInterface.isUp()||networkInterface.getDisplayName().contains("VM")){
                    //继续下次循环
                    continue;
                }
                for(Enumeration<InetAddress> inetAddressEnumeration=networkInterface.getInetAddresses();inetAddressEnumeration.hasMoreElements();){
                    InetAddress inetAddress=inetAddressEnumeration.nextElement();
                    //如果此IP不为空
                    if(inetAddress!=null){
                        //如果此IP为IPV4 则返回
                        if(inetAddress instanceof Inet4Address){
                            return inetAddress;
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "请连接网络后使用！");
            System.exit(0);
            return null;
        }catch(SocketException e){
            e.printStackTrace();
            return null;
        }
    }
}
