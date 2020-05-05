package me.circlehotarux.filecopy;

import java.io.*;

public class ioTest {
    public static void main(String args[]) {

    String s_FileName = "e:/test.txt";
    String s_DFileName = "e:/test1.txt";
    try {
      //定义输入流
      FileInputStream fis =
          new FileInputStream(s_FileName);
      BufferedInputStream bis =
          new BufferedInputStream(fis);
      //定义输出流
      FileOutputStream fos =
          new FileOutputStream(s_DFileName);
      BufferedOutputStream bos =
          new BufferedOutputStream(fos);
      DataOutputStream dos =
          new DataOutputStream(bos);
      int b;
      while ( (b = bis.read()) != -1) {
        dos.write(b);
      }
      bis.close();
      dos.close();
    }

    catch (IOException e) {
      System.err.println(e);
    }
  }
}
