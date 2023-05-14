package GUI.panel;


import SQL.Impl.ConfigDAO;
import SQL.data.Config;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkPanel {
    ConfigDAO configDAO = new ConfigDAO();
    private ConfigPanel()
    {
        super();
        init();
    }
    public static ConfigPanel instance = new ConfigPanel();
    private final JTextField tfBudget = new JTextField();
    private final JTextField tfPath = new JTextField();
    private final JLabel lBudget = new JLabel("本月预算：");
    private final JLabel lPath = new JLabel("Mysql安装路径");
    private final JButton bRefresh = new JButton("更新");

    private void init() {

        lBudget.setBounds(50,20,300,10);
        tfBudget.setBounds(50,30,300,20);
        tfPath.setBounds(50,50,300,10);
        lPath.setBounds(50,60,300,20);
        bRefresh.setBounds(50,100,40,35);
        tfBudget.setPreferredSize(new Dimension(300,20));
        tfPath.setPreferredSize(new Dimension(300,20));
        lBudget.setPreferredSize(new Dimension(350,20));
        lPath.setPreferredSize(new Dimension(350,20));
        bRefresh.addActionListener(e->{

        });
        testInit();
        listenerInit();
        add(lBudget);
        add(tfBudget);
        add(lPath);
        add(tfPath);
        add(bRefresh);

    }
    private void testInit()
    {
        Config c = configDAO.get();
        tfBudget.setText(String.valueOf(c.getBudget()));
        tfPath.setText(c.getSQL_Path());
    }
    private void listenerInit()
    {
        bRefresh.addActionListener(e->
                configDAO.update(new Config(Integer.parseInt(tfBudget.getText()),tfPath.getText())));
    }
}
