/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IOTest1;

/**
 *
 * @author shuqing
 */
import java.io.*;
import java.util.*;

public class iotest1 {
  private ArrayList list = new ArrayList();

  public iotest1(String fn) throws IOException {
    FileReader fr = new FileReader(fn);
    BufferedReader br = new BufferedReader(fr);
    String ln;
    while ( (ln = br.readLine()) != null) {
      list.add(ln);
    }
    br.close();
  }

  public String getLine(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }

    return (n < list.size() ?
            (String) list.get(n) : null);
  }

  public static void main(String args[]) {
    String s_FileName = "test.txt";
    try {
      iotest1 lc = new iotest1(s_FileName);
      int i = 0;
      String ln;
      while ( (ln = lc.getLine(i++)) != null) {
        System.out.println(ln);
      }
    }
    catch (IOException e) {
      System.err.println(e);
    }
  }
}
