package ch07.section01;

import java.io.*;

public class ioTest {
  public static void main(String args[]) {
    String s_FileName = "c:/test.txt";
    String s_DFileName = "c:/test1.txt";
    try {
      //����������
      FileInputStream fis =
          new FileInputStream(s_FileName);
      BufferedInputStream bis =
          new BufferedInputStream(fis);
      //���������
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
