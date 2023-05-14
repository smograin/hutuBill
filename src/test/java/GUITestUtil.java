
import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class GUITestUtil {
    private void test(JPanel... js)
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

}
