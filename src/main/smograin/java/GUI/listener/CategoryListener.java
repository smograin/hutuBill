package GUI.listener;

import util.GUIUtil;
import GUI.panel.CategoryPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CategoryPanel.instance.refresh();
        GUIUtil.updateWorkplacePanel(CategoryPanel.instance);
    }
}
