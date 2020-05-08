/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.main;

import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import transfer.data.Configs;
import transfer.data.DataList;
import transfer.thread.DetectThread;
import transfer.thread.ExposeThread;
import transfer.thread.ServerAcceptThread;

/**
 *
 * @author circlehotarux
 */
public class MainUI extends javax.swing.JFrame {
    //UDP广播端口
    private DatagramSocket udpSocket;
    //侦测/暴露线程
    private ExposeThread exposeThread;
    private DetectThread detectThread;
    //TCP接收线程
    private ServerAcceptThread serverAcceptThread;

    //配置文件
    private File configFile=new File(".","config.cnf");

    //线程锁
    private Object lock;
    private Runnable refresh;
    private Thread thread;
    //盒子布局
    private Box verticalBox;
    
    /**
     * Creates new form MainUI
     */
    public MainUI() {
        initComponents();
        this.initConfig();
        this.initUDPThread();
        this.initTCPThread();
        //开启线程显示侦测信息--防止卡顿主线程
        new Thread(refresh).start();
        //初始化线程锁与线程对象
        initThreadLock();
    }

    /*
    初始化读取配置文件
     */
    private void initConfig(){
        DataInputStream dataInputStream=null;
        try{
            dataInputStream=new DataInputStream(new FileInputStream(configFile));
            String config=dataInputStream.readUTF();
            if(config==null||config.equals(""))
                    throw new Exception();
            else{
                Configs.fromFile(config);
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"读取配置文件失败！","Tips：",JOptionPane.ERROR_MESSAGE);
        }
    }
        /*
   TCP接收线程初始化--默认开启
    */
    private void initTCPThread(){
        try {
            serverAcceptThread = new ServerAcceptThread(new ServerSocket(54199));
            serverAcceptThread.start();
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"端口被占用/或已经运行！");
        }
    }
    /*
    UDP广播初始化--默认开启
     */
    private void initUDPThread(){
        try {
            udpSocket = new DatagramSocket(54188);
            exposeThread = new ExposeThread(udpSocket);
            detectThread = new DetectThread(udpSocket);

            //启动线程
            //加载配置文件
            exposeThread.start();
            detectThread.start();

        }catch(SocketException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"程序已运行！","Tip",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"程序打开失败！");
        }
    }
    /*
    ---之所以不直接用字符流，是因为不想让人随意查看配置文件--抬高一点逼格,然并卵...haha...
     */
    private void saveConfigs(){
        DataOutputStream dataOutputStream=null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(configFile));
            dataOutputStream.writeUTF(Configs.toFile());
            dataOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            try {
                if (dataOutputStream!=null)
                    dataOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /*
    切换面板
     */
    private void changePane(JPanel panel){
        this.remove(this.getContentPane());
        this.setContentPane(panel);
        //重新布局组件
        this.validate();
    }
    /*
    初始化线程锁及线程操作对象
     */
    private void initThreadLock(){
        lock=new Object();
        refresh=new Runnable(){
            public void run(){
                //加锁
                synchronized (lock) {
                    verticalBox.removeAll();
                    verticalBox.validate();
                    verticalBox.repaint();
                    scrollPane.validate();
                    MainUI.this.showExposeUser();
                }
            }
        };
    }
    /*
    显示暴露用户
     */
    private void showExposeUser(){
        //遍历集合数据
        for(String ipTemp: DataList.detectList){
            SubUI subUITemp=new SubUI(ipTemp);
            subUITemp.setPreferredSize(new Dimension(560,33));
            subUITemp.setMaximumSize(new Dimension(560,33));
            subUITemp.setMinimumSize(new Dimension(560,33));
            verticalBox.add(subUITemp);
            verticalBox.add(Box.createVerticalStrut(3));
            verticalBox.validate();
            scrollPane.validate();
        }
        if(DataList.detectList.size()==0)
            JOptionPane.showMessageDialog(null,"暂时没有侦测到用户，请稍后刷新重试！","Tips：",JOptionPane.PLAIN_MESSAGE);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DetectPane = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        testButton = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        bottlePanel = new javax.swing.JPanel();
        fileDirectoryTip = new javax.swing.JLabel();
        settingButton = new javax.swing.JButton();
        fileDirectory = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DetectPane.setLayout(new java.awt.BorderLayout());
        verticalBox=Box.createVerticalBox();

        scrollPane.setBorder(null);
        scrollPane.getViewport().add(verticalBox);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        DetectPane.add(scrollPane, java.awt.BorderLayout.CENTER);

        testButton.setText("刷新");
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });
        DetectPane.add(testButton, java.awt.BorderLayout.LINE_END);

        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("文件传输助手");
        DetectPane.add(title, java.awt.BorderLayout.PAGE_START);

        fileDirectoryTip.setText("文件保存地址:");

        settingButton.setText("设置");
        settingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingButtonActionPerformed(evt);
            }
        });

        fileDirectory.setEditable(false);
        //加载配置文件信息
        fileDirectory.setText(Configs.getFileDiretory());

        javax.swing.GroupLayout bottlePanelLayout = new javax.swing.GroupLayout(bottlePanel);
        bottlePanel.setLayout(bottlePanelLayout);
        bottlePanelLayout.setHorizontalGroup(
            bottlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileDirectoryTip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileDirectory, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(settingButton)
                .addContainerGap())
        );
        bottlePanelLayout.setVerticalGroup(
            bottlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileDirectoryTip)
                    .addComponent(settingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        DetectPane.add(bottlePanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DetectPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DetectPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed
        // TODO add your handling code here:
        //开启子线程防止卡顿主线程-影响用户体验
        if(thread==null){
            thread=new Thread(refresh);
            thread.start();
        }else if(!thread.isAlive()){
                thread=new Thread(refresh);
                thread.start();
            } else{
            //不处理
        }
    }//GEN-LAST:event_testButtonActionPerformed

    private void settingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingButtonActionPerformed
        JFileChooser jFileChooser=new JFileChooser(fileDirectory.getSelectedText());
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setMultiSelectionEnabled(false);
        int result=jFileChooser.showOpenDialog(this);
        if(result==JFileChooser.APPROVE_OPTION){
            fileDirectory.setText(jFileChooser.getSelectedFile().getAbsolutePath());
        }
        //文件保存目录
        String directory=fileDirectory.getText().trim();
        File testFile=new File(directory);
        //判断输入是否正确
        if(directory!=null&&!directory.equals("")&&testFile.exists()&&testFile.isDirectory()){
            //更新配置信息
            Configs.setFileDirectory(directory);
            saveConfigs();
        }else{
            JOptionPane.showMessageDialog(null,"请输入正确的文件保存路径！","Tips：",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_settingButtonActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DetectPane;
    private javax.swing.JPanel bottlePanel;
    private javax.swing.JTextField fileDirectory;
    private javax.swing.JLabel fileDirectoryTip;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton settingButton;
    private javax.swing.JButton testButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
