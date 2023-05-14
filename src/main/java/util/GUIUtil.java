package util;


import GUI.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class GUIUtil {
    private static final String imgPath = "src/main/resources/static/img/";
    public static void setImageIcon(JButton button, String fileName, String tip)
    {
        button.setIcon(new ImageIcon(imgPath+fileName));
        button.setPreferredSize(new Dimension(70,75));
        button.setToolTipText(tip);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setBorder(null);
        button.setContentAreaFilled(false);
        button.setIconTextGap(1);
        button.setText(tip);
    }

    public static void setImageIcon(JButton[] jButtons,String[] fileNames,String[] tips) throws NumberException
    {
        int length = fileNames.length;
        if(jButtons.length== length & length == tips.length)
            throw new NumberException("数据对不上");
        for (int i = 0; i < length; i++) {
            setImageIcon(jButtons[i],fileNames[i],tips[i]);
        }
    }

    public static boolean isEmpty(JTextField tf, String input)
    {
        String text = tf.getText().trim();
        if(text.length()==0)
        {
            JOptionPane.showMessageDialog(null,input+" 不能为空");
            return true;
        }
        return false;
    }

    public static boolean isNumber(JTextField tf, String input)
    {
        if(isEmpty(tf,input)) return false;
        String i = tf.getText().trim();
        try
        {
            Integer.parseInt(i);
            return true;
        }catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,input +" 需要是整数");
            tf.grabFocus();
            return false;
        }
    }

    public static boolean isZero(JTextField tf,String input)
    {
        if(isEmpty(tf,input)) return false;
        if(Integer.parseInt(tf.getText().trim())==0)
        {
            JOptionPane.showMessageDialog(null," 不能是0");
            tf.grabFocus();
            return true;
        }
        return true;
    }

    public static void updateWorkplacePanel(JPanel j)
    {
        MainPanel.instance.setWorkplace(j);
    }
    public static void setSize()
    {

    }
    public static void setColor(Color color,JComponent... cs)
    {
        for (JComponent c : cs)
        {
            c.setForeground(color);
        }
    }
    public static void useLNF()
    {
        try
        {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                 InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void PanelTest(JPanel... js)
    {
        JFrame J = new JFrame();
        J.setSize(300,400);
        J.setLocation(0,0);
        for (JPanel j: js) {
            J.add(j);
        }
        J.setDefaultCloseOperation(EXIT_ON_CLOSE);
        J.setVisible(true);
    }
    public static void addWithSeparator(JButton b,JToolBar tb,int separator)
    {

        for (int i = 0; i <separator; i++) {
            tb.addSeparator();
        }
        tb.add(b);
    }
    public static void addWithSeparator(JToolBar tb,int separator,JButton... bs)
    {
        for (JButton b : bs)
        {
            addWithSeparator(b,tb,separator);
        }
    }
    public static void setBorderLayout(JComponent component)
    {
        component.setLayout(new BorderLayout());
    }

    public static int ejectOptionPane(Component parent,String message)
    {
        return JOptionPane.showConfirmDialog(parent,message);
    }
}