
import java.io.*;
public class Test
{
 static final String file1="D:/w1.txt";
 static final String file2="D:/w2.txt";
 static final String file3="D:/w3.txt";
 public static void main(String args[])throws IOException
 {
  /*//����System.in
  int data;
  while ((data=System.in.read())!=-1)//Ctrl+c��������
  {
   System.out.write(data);
  }*/
  //����ĳ����������file1�ļ�д��,�������ݵ�һ���ָ��Ƶ�file2
  //�ٰ�file2�ļ������ظ��Ƶ�file3,����ӡ����
  FileOutputStream fos1=new FileOutputStream(file1);
  FileOutputStream fos2=new FileOutputStream(file2);
  FileOutputStream fos3=new FileOutputStream(file3);
  fos1.write("������2008��8��3��,�뱱�����˻ỹ��5��,����ǳ�������.".getBytes());
  fos1.close();
  FileInputStream fis1=new FileInputStream(file1);
  fis1.skip(19);//����19���ֽ�
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
  System.out.println("���Ƴɹ�!");
  fis2.close();
  fos3.close();
  
 }
} 
