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
public class MultiServerThreadTest
    extends Thread {
  private Socket socket = null;
  public MultiServerThreadTest(Socket socket) {
    super("MultiServerThreadTest");
    this.socket = socket;
  }
  public void run() {
    try {
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(
          socket.getInputStream()));
      String inputLine, outputLine;
      ServerProtocolTest kkp = new ServerProtocolTest();
      outputLine = kkp.processInput(null);
      out.println(outputLine);
      while ( (inputLine = in.readLine()) != null) {
        outputLine = kkp.processInput(inputLine);
        out.println(outputLine);
        if (outputLine.equals("Bye")) {
          break;
        }
      }
      out.close();
      in.close();
      socket.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
