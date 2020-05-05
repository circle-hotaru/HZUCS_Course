package IO;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class new_ioTest1 {
	private ArrayList list = new ArrayList();

	public new_ioTest1(String fn) throws IOException {
		FileReader fr = new FileReader(fn);
		BufferedReader br = new BufferedReader(fr);
		String ln;
		while ((ln = br.readLine()) != null) {
			list.add(ln);
		}
		br.close();
	}

	public String getLine(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}

		return (n < list.size() ? (String) list.get(n) : null);
	}

	public static void main(String args[]) {
		MyFrame mf = new MyFrame();
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	mf.pack();
		mf.setVisible(true);
	}
	
	public static void read(){
		try {
			new_ioTest1 lc = new new_ioTest1(MyFrame.file);
			int i = 0;
			String ln;
			while ((ln = lc.getLine(i++)) != null) {
				MyFrame.ta.setText(ln);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void save(){
		String s_FileName = MyFrame.file;
	    String s_DFileName = MyFrame.saveFile;
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

class MyFrame extends JFrame {
	JFileChooser jfc = null;
	public static String file = null;
	public static String saveFile = null;
	public static TextArea ta = new TextArea();
	public MyFrame() {
		JPanel jp = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu menu = new JMenu("�ļ�");
		JMenu menu1 = new JMenu("�༭");
		JMenu menu2 = new JMenu("��ʽ");
		JMenu menu3 = new JMenu("�鿴");
		JMenu menu4 = new JMenu("����");
		menuBar.add(menu);
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);

		JMenuItem openItem = new JMenuItem("��");
		JMenuItem saveItem = new JMenuItem("���Ϊ");
		JMenuItem exitItem = new JMenuItem("�˳�");
		menu.add(openItem);
		menu.add(saveItem);
		menu.add(exitItem);

		openItem.addActionListener(new FileOpenListener());
		saveItem.addActionListener(new FileOpenListener());
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		jfc = new JFileChooser();
		
		jp.add(ta);
		
		this.add(jp,BorderLayout.SOUTH);
		this.setTitle("�ļ�����");
		this.setSize(600, 260);
		
	}

	private class FileOpenListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("��")){
				jfc.setCurrentDirectory(new File("."));
				int open = jfc.showOpenDialog(MyFrame.this);
				if(open == JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile().getPath();
					new_ioTest1.read();
				}
			}
			if(e.getActionCommand().equals("���Ϊ")){
				int save = jfc.showSaveDialog(MyFrame.this);
				if(save == JFileChooser.APPROVE_OPTION){
					saveFile = jfc.getSelectedFile().getPath();
					new_ioTest1.save();
				}
			}
		}

	}
}
