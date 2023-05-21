package GUI;

import GUI.panel.MainPanel;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static
    {
        GUIUtil.useLNF();
    }
    private MainFrame()
    {
        setLayout(new BorderLayout());
        setTitle("一本糊涂账");
        setSize(520,420);
        setLocation(200,300);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(MainPanel.instance,BorderLayout.CENTER);
    }
    public static MainFrame instance = new MainFrame();
}
