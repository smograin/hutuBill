package GUI.panel;


import SQL.Impl.ConfigDAO;
import SQL.data.Config;
import util.GUIUtil;

import javax.swing.*;

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
    private final JButton bChooser = new JButton("选择MySQL路径");
    private final JButton bRefresh = new JButton("更新");
    private final JFileChooser fileChooser = new JFileChooser();

    private void init() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        textInit();
        listenerInit();
        add(lBudget);
        add(tfBudget);
        add(lPath);
        add(tfPath);
        add(bChooser);
        add(bRefresh);

    }
    private void textInit()
    {
        Config c = configDAO.get();
        tfBudget.setText(String.valueOf(c.getBudget()));
        tfPath.setText(c.getSQL_Path());
    }
    private void listenerInit()
    {
        bRefresh.addActionListener(e->
                {
                    if(GUIUtil.isPath(tfPath.getText(),this))
                    {
                        configDAO.update(new Config(Integer.parseInt(tfBudget.getText()), tfPath.getText()));
                        JOptionPane.showMessageDialog(this,"更新成功");
                    }
                }
                );
        bChooser.addActionListener(e->{
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                tfPath.setText(fileChooser.getSelectedFile().getPath());
            }
        });
    }
}
