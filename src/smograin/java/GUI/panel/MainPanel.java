package GUI.panel;

import GUI.listener.*;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    JToolBar tb = new JToolBar();
    JButton bBackup = new JButton();
    JButton bCategory = new JButton();
    JButton bConfig = new JButton();
    JButton bRecord = new JButton();
    JButton bRecover = new JButton();
    JButton bSpend = new JButton();

    JPanel workplace = new JPanel();
    public static final int WORKPLACE_WIDTH = 450;
    public static final int WORKPLACE_HEIGHT = 260;
    public void setWorkplace(JPanel j)
    {
        workplace.removeAll();
        workplace.add(j);
        workplace.validate();
        workplace.repaint();
    }
    private void listenerInit()
    {
        bCategory.addActionListener(new CategoryListener());
        bSpend.addActionListener(new SpendListener());
        bBackup.addActionListener(new BackupListener());
        bConfig.addActionListener(new ConfigListener());
        bRecover.addActionListener(new RecoverListener());
    }
    private void init()
    {
        GUIUtil.setImageIcon(bSpend,"home.png","消费一览");
        GUIUtil.setImageIcon(bRecover,"recover.png","恢复");
        GUIUtil.setImageIcon(bRecord,"record.png","记一笔");
        GUIUtil.setImageIcon(bConfig,"config.png","设置");
        GUIUtil.setImageIcon(bCategory,"category.png","消费分类");
        GUIUtil.setImageIcon(bBackup,"backup.png","备份");

        GUIUtil.addWithSeparator(bSpend,tb,1);
        GUIUtil.addWithSeparator(bRecord,tb,1);
        GUIUtil.addWithSeparator(bCategory,tb,1);
        GUIUtil.addWithSeparator(bConfig,tb,1);
        GUIUtil.addWithSeparator(bBackup,tb,1);
        GUIUtil.addWithSeparator(bRecover,tb,1);

        workplace.setPreferredSize(new Dimension(MainPanel.WORKPLACE_WIDTH,MainPanel.WORKPLACE_HEIGHT));
        tb.setPreferredSize(new Dimension(510,100));
        tb.setFloatable(false);
        add(tb,BorderLayout.NORTH);
        add(workplace,BorderLayout.CENTER);
    }
    private MainPanel()
    {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500,400));
        init();
        listenerInit();
    }
    public static MainPanel instance = new MainPanel();


}
