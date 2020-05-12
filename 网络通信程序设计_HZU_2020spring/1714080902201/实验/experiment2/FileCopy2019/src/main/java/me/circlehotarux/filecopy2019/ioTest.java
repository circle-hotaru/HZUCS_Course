package me.circlehotarux.filecopy2019;

import java.io.*;

public class ioTest extends Thread{
    String s_FileName = null;
    String s_DFileName = null;
    
    public ioTest(String sF, String sD){
        s_FileName = sF;
        s_DFileName = sD;
    }
            
    public void run() {
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
