package transfer.dialogs;

import transfer.utils.CommonUtils;
import javax.swing.*;
import java.awt.*;

public class ProgressBarDialog extends BaseDialog {
    private JProgressBar progressBar;
    private JLabel loadTip;

    private boolean sign;
    //进度条最大值
    private int max;
    public ProgressBarDialog(int max,boolean sign){
        super();
        this.sign=sign;
        this.max=max;
        progressBar.setMaximum(max);
        if(sign) {
            this.setTitle("上传进度");
            loadTip.setText("不可关闭，正在上传···");
        }
        else {
            this.setTitle("下载进度");
            loadTip.setText("不可关闭，正在下载···");
        }

    }
    @Override
    protected void initDialog(){
        super.initDialog();


        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(250,70);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setModal(false);
        this.setBackground(new Color(0,0,0,0.30f));
        //设置不可关闭
        this.setDefaultCloseOperation(ProgressBarDialog.DO_NOTHING_ON_CLOSE);
    }
    @Override
    protected void initViews() {
        progressBar=new JProgressBar();
        //progressBar.setValue(50);
        //设置显示百分比
        progressBar.setStringPainted(true);

        loadTip =new JLabel();
        loadTip.setForeground(Color.WHITE);
    }

    @Override
    protected void addComponents() {
        this.add(progressBar);
        this.add(loadTip);
    }

    @Override
    protected void addListeners() {
            progressBar.addChangeListener(e->{
                //获取百分比
                if(progressBar.getPercentComplete()==1){
                    System.out.println(progressBar.getPercentComplete());
                    this.dispose();
                }
            });
    }
    public void loadProgressBar(int size){
            progressBar.setValue(progressBar.getValue()+size);
    }
}
