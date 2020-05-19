/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.circlehotarux.multicast;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author circlehotarux
 */

class SonServerThreadTest
    extends ServerThread {
  private long FIVE_SECONDS = 5000;
  public SonServerThreadTest() throws IOException {
    super("SonServerThreadTest");
  }

  public void run() {
    while (moreQuotes) {
      try {
        byte[] buf = new byte[256];
        // construct quote
        String dString = null;
        if (in == null) {
          dString = new Date().toString();
        }
        else {
          dString = getNextQuote();
        }
        buf = dString.getBytes();
        // send it
        InetAddress group = InetAddress.getByName("224.0.2.8");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group,
            4446);
        socket.send(packet);
        // sleep for a while
        try {
          sleep( (long) (Math.random() * FIVE_SECONDS));
        }
        catch (InterruptedException e) {}
      }
      catch (IOException e) {
        e.printStackTrace();
        moreQuotes = false;
      }
    }
    socket.close();
  }
}