package transfer.utils;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.Enumeration;

public class CommonUtils {
    private static Toolkit toolkit=Toolkit.getDefaultToolkit();
    /*
       获取自定义大小Image图片对象
     */
    public static Image getDIVImage(int width,int height,String src){
        Image image=toolkit.getImage(CommonUtils.class.getResource(src));
//        SCALE_FAST 一种图像缩放方法
        return image.getScaledInstance(width,height,Image.SCALE_FAST);
    }
    public static Image getIamge(String src){
        return toolkit.getImage(CommonUtils.class.getResource(src));
    }

    /*
    设置JFrame背景
    前提-需要内容面板透明
    框架-载体地址-图片
     */
    public static void setBackground(JFrame jFrame,JLabel jLabel,Image image){
//       JLayeredPane 分层面板
        JLayeredPane jLayeredPane=jFrame.getLayeredPane();
        jLayeredPane.remove(jLabel);
        jLabel.setIcon(new ImageIcon(image));
        jLabel.setBounds(0,0,jFrame.getWidth(),jFrame.getHeight());
        jLayeredPane.add(jLabel,new Integer(Integer.MIN_VALUE));

        jFrame.validate();
        jFrame.repaint();
    }
    /*
    广播地址
     */
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
    /*
    获取本机网内地址
     */
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
