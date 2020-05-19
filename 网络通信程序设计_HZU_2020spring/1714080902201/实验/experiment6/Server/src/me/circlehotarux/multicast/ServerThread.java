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


class ServerThread
    extends Thread {
  protected DatagramSocket socket = null;
  protected BufferedReader in = null;
  protected boolean moreQuotes = true;
  public ServerThread() throws IOException {
    this("ServerThread");
  }
  public ServerThread(String name) throws IOException {
    super(name);
    socket = new DatagramSocket(4445);
    try {
      in = new BufferedReader(new FileReader("c:/Users/circlehotarux/test.txt"));
    }
    catch (FileNotFoundException e) {
      System.err.println("Could not open quote file. Serving time instead.");
    }
  }
  public void run() {
    while (moreQuotes) {
      try {
        byte[] buf = new byte[256];
        // receive request
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        // figure out response
        String dString = null;
        if (in == null) {
          dString = new Date().toString();
        }
        else {
          dString = getNextQuote();
        }
        buf = dString.getBytes();
        // send the response to the client at "address" and "port"
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
      }
      catch (IOException e) {
        e.printStackTrace();
        moreQuotes = false;
      }
    }
    socket.close();
  }
  protected String getNextQuote() {
    String returnValue = null;
    try {
      if ( (returnValue = in.readLine()) == null) {
        in.close();
        moreQuotes = false;
        returnValue = "No more quotes. Goodbye.";
      }
    }
    catch (IOException e) {
      returnValue = "IOException occurred in server.";
    }
    return returnValue;
  }
}