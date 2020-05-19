/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.circlehotarux.multicast;

import java.io.*;
import java.net.*;
/**
 *
 * @author circlehotarux
 */

public class ClientTest {
  public static void main(String[] args) throws IOException {
    MulticastSocket socket = new MulticastSocket(4446);
    InetAddress address = InetAddress.getByName("224.0.2.8");
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
