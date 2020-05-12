package me.circlehotarux.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientTest {
    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket(4446);
        InetAddress address = InetAddress.getByName("228.5.6.8");
        socket.joinGroup(address);
        DatagramPacket packet;
        for (int i = 0; i < 5; i++) {
            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(packet.getData());
            System.out.println("Quote of the Moment: " + received);
        }
        socket.leaveGroup(address);
        socket.close();
    }
}
