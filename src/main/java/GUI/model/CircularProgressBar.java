package GUI.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircularProgressBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private int progress = 0;

    public void updateProgress(int value) {
        this.progress = value;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height); // 圆环直径为宽度和高度中的较小值

        // 计算圆环在 JFrame 中居中的位置
        int x = (this.getWidth() - diameter) / 2;
        int y = (this.getHeight() - diameter) / 2;

        // 画圆环
        int strokeWidth = Math.round(diameter / 10f); // 笔刷粗细设置为圆环直径的 1/10
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(new Color(224, 224, 224));
        g2d.drawOval(x + strokeWidth / 2, y + strokeWidth / 2, diameter - strokeWidth, diameter - strokeWidth);

        // 画圆环进度条
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.BLUE);
        float angle = 360 * progress / 100f;
        g2d.drawArc(x + strokeWidth / 2, y + strokeWidth / 2, diameter - strokeWidth, diameter - strokeWidth, -90, Math.round(angle));

        // 画中间的百分比文字
        g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, diameter / 5));
        String msg = String.format("%d%%", progress);
        int stringWidth = g2d.getFontMetrics().stringWidth(msg);
        g2d.drawString(msg, x + (diameter - stringWidth) / 2, y + (diameter + diameter / 5) / 2);

        g2d.dispose();
    }
}