package GUI.panel;

import SQL.Impl.ConfigDAO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

import static util.SQLUtil.*;

public class RecoverPanel extends WorkPanel {
    private final String cmd = String.format("/bin/mysql.exe\" -u%s -p%s %s < ", user, password, database);
    private final JButton bRecover = new JButton("还原");
    private final JFileChooser fcOpenFile = new JFileChooser();
    private void listenerInit()
    {
        bRecover.addActionListener(e->
        {
            int result = fcOpenFile.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fcOpenFile.getSelectedFile();
                System.out.println(selectedFile.getPath());
                //TODO read the selectedFile and recover it by execute sql words

                String mysqlPath = new ConfigDAO().get().getSQL_Path();
                String run = String.format("cmd /c \"%s%s%s",mysqlPath,cmd,selectedFile.getAbsolutePath());
                System.out.println(run);
                try {
                    Process runtimeProcess = Runtime.getRuntime().exec(run);
                    int processComplete = runtimeProcess.waitFor();

                    if (processComplete == 0) {
                        System.out.println("还原成功");
                    } else {
                        System.out.println("还原失败");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
    }
    private void init()
    {
        add(bRecover, BorderLayout.NORTH);
        fcOpenFile.setFileFilter(new FileNameExtensionFilter("SQL 文件 (*.sql)", "sql"));
    }
    private RecoverPanel()
    {
        listenerInit();
        init();
    }
    public static RecoverPanel instance = new RecoverPanel();
}
