package GUI.listener;

import GUI.panel.RecoverPanel;
import util.GUIUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecoverListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GUIUtil.updateWorkplacePanel(RecoverPanel.instance);
    }
}
