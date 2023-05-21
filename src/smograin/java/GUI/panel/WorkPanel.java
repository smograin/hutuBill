package GUI.panel;

import javax.swing.*;
import java.awt.*;

public abstract class WorkPanel extends JPanel {
    WorkPanel()
    {
        setPreferredSize(new Dimension(MainPanel.WORKPLACE_WIDTH,MainPanel.WORKPLACE_HEIGHT));
    }
}
