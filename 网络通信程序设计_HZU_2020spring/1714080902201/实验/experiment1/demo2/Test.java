
import java.io.*;
public class Test
{
 static final String file1="D:/w1.txt";
 static final String file2="D:/w2.txt";
 static final String file3="D:/w3.txt";
 public static void main(String args[])throws IOException
 {
  /*//关于System.in
  int data;
  while ((data=System.in.read())!=-1)//Ctrl+c结束输入
  {
   System.out.write(data);
  }*/
  //下面的程序段用以向file1文件写入,把其内容的一部分复制到file2
  //再把file2文件完整地复制到file3,并打印出来
  FileOutputStream fos1=new FileOutputStream(file1);
  FileOutputStream fos2=new FileOutputStream(file2);
  FileOutputStream fos3=new FileOutputStream(file3);
  fos1.write("今天是2008年8月3号,离北京奥运会还有5天,心里非常激动啊.".getBytes());
  fos1.close();
  FileInputStream fis1=new FileInputStream(file1);
  fis1.skip(19);//跳过19个字节
  byte[] buf=new byte[fis1.available()];
  fis1.read(buf);
  fis1.close();
  System.out.println(new String(buf));
  fos2.write(buf);
  fos2.close();
  FileInputStream fis2=new FileInputStream(file2);
  while(fis2.available()>0)
  {
   byte[] b=new byte[fis2.available()];
   int let=fis2.read(b);
   if(let==-1)break;
   fos3.write(b,0,let);
  }
  System.out.println("复制成功!");
  fis2.close();
  fos3.close();
  
 }
} 
