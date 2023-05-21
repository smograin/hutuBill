package GUI.listener;

import GUI.panel.ConfigPanel;
import util.GUIUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GUIUtil.updateWorkplacePanel(ConfigPanel.instance);
    }
}
