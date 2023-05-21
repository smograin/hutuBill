package GUI.listener;

import GUI.panel.BackupPanel;
import util.GUIUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GUIUtil.updateWorkplacePanel(BackupPanel.instance);
    }
}
