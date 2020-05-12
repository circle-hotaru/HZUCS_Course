package transfer.dialogs;

import javax.swing.*;
import java.awt.*;

public class LoadDialog extends BaseDialog {
    private JLabel loadTip;
    @Override
    protected void initDialog() {
        super.initDialog();
        this.setTitle("请稍后···");
        this.setModal(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(300,40);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);

        this.setBackground(new Color(0,0,0,0.30f));
        this.setDefaultCloseOperation(LoadDialog.DO_NOTHING_ON_CLOSE);

    }

    @Override
    protected void initViews() {
        loadTip=new JLabel("等待对方操作···");
        loadTip.setForeground(Color.WHITE);
    }

    @Override
    protected void addComponents() {
            this.add(loadTip);
    }
    @Override
    protected void addListeners() {
    }
}
