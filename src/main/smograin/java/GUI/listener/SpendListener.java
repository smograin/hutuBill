package GUI.listener;

import GUI.panel.SpendPanel;
import util.GUIUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpendListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        GUIUtil.updateWorkplacePanel(SpendPanel.instance);
    }
}
