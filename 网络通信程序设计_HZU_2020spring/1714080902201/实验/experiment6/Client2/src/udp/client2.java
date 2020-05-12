package udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class client2 {
    public static void main(String[] args) throws Exception {
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;
        mcIPAddress = InetAddress.getByName(mcIPStr);
        mcSocket = new MulticastSocket(mcPort);
        System.out.println("Multicast Receiver running at:"
                + mcSocket.getLocalSocketAddress());
        mcSocket.joinGroup(mcIPAddress);

        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        System.out.println("Waiting for a  multicast message...");
        mcSocket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(),
                packet.getLength());
        System.out.println("[Multicast  Receiver] Received:" + msg);

        mcSocket.leaveGroup(mcIPAddress);
        mcSocket.close();
    }
}
