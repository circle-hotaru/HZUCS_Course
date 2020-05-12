import java.io.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class IoTest {

	protected Shell shell;
	private Text text;
	
	public static void main(String[] args) {
		try {
			IoTest window = new IoTest();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	protected void createContents() {
		shell = new Shell();
		shell.setBounds(300, 300, 300, 300);
		shell.setText("\u64CD\u4F5C\u754C\u9762");
		{
			Button btnNewButton = new Button(shell, SWT.NONE);
			btnNewButton.addMouseListener(new MouseAdapter() {
				public void mouseDown(MouseEvent e) {
					FileDialog dialog = new FileDialog(shell, SWT.OPEN);
					dialog.setFilterExtensions(new String[] { "*.txt", ".doc", "*.*" }); 
					dialog.setFilterPath("D:\\A"); 
					String fileName = dialog.open();
					if (fileName != null) {
						try {
							FileReader fr = new FileReader(fileName);
							BufferedReader br = new BufferedReader(fr);
							while(br.readLine() != null) {
								String str = br.readLine();
								text.setText(str + '\n');
							}
						} catch(IOException ioe) {
							MessageDialog.openInformation(shell, "����", "�ļ�δ�ҵ���");
						}
					} else {
						return;
					}
				}
			});
			btnNewButton.setBounds(10, 210, 120, 37);
			btnNewButton.setText("\u8BF7\u9009\u62E9\u8981\u6253\u5F00\u7684\u6587\u4EF6");
		}
		{
			Button button = new Button(shell, SWT.NONE);
			button.addMouseListener(new MouseAdapter() {
				public void mouseDown(MouseEvent e) {
					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
					dialog.setFilterExtensions(new String[] { "*.txt", ".doc", "*.*" });
					dialog.setFilterPath("D:\\A"); 
					String fileName = dialog.open();
					if (fileName != null) {
						if(text.getText() != null) {
							BufferedReader br = new BufferedReader(new StringReader(text.getText()));
							try {
								FileWriter fw = new FileWriter(fileName, true);
								BufferedWriter pw = new BufferedWriter(fw);
								String str = "";
								while((str = br.readLine()) != null) {
									pw.write(str);
									pw.newLine();
									pw.flush();
									text.setText("");
								}
								br.close();
								fw.close();
								pw.close();
							} catch(IOException ioe) {
								ioe.printStackTrace();
							}
						}
					} else {
						return;
					}
				}
			});
			button.setBounds(150, 210, 120, 37);
			button.setText("\u8BF7\u9009\u62E9\u8981\u4FDD\u5B58\u7684\u8DEF\u5F84");
		}
		{
			text = new Text(shell, SWT.BORDER);
			text.setBounds(10, 10, 260, 184);
		}
	}
}
