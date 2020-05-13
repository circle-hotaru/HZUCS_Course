/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.circlehotarux.protocol;

import java.io.*;
import java.net.*;

/**
 *
 * @author circlehotarux
 */
public class MultiServerTest {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    boolean listening = true;
    try {
      serverSocket = new ServerSocket(5555);
    }
    catch (IOException e) {
      System.err.println("Could not listen on port: 5555.");
      System.exit( -1);
    }
    while (listening) {
      new MultiServerThreadTest(serverSocket.accept()).start();
    }
    serverSocket.close();
  }
}
