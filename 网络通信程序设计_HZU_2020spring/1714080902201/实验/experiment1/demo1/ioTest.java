/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package 文件流操作;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class ioTest  {
	public static void Input(String fromFilename,String toFilename) {
		  String s_FileName =fromFilename ;//输入文件路径
		  String s_DFileName =toFilename;  //输出文件路径
		  FileInputStream fis=null;
		  BufferedInputStream  bis=null;
		  FileOutputStream fos=null;
		  BufferedOutputStream bos=null;
		  DataOutputStream dos=null;
	       try {
	    	  System.out.println("经过方法！");
		      //定义输入流
		      fis = new FileInputStream(s_FileName);
 bis = new BufferedInputStream(fis);
		      //定义输出流
		      fos = new FileOutputStream(s_DFileName);
		      bos = new BufferedOutputStream(fos);
		      dos = new DataOutputStream(bos);
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
		    finally
		    {
		    	if(bis!=null)
		    	{
		    		 try {
						bis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		 bis =null;
		    	}
		        else if(dos!=null)
		        {
		        	try {
dos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	dos=null;
		        }
		    }
		 
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ioTest test = new ioTest();
		ioTest.Input("D:/test.txt", "D:/test1.txt");
		ioTest.Input("D:/test1.txt", "D:/test.txt");
	}    
}
