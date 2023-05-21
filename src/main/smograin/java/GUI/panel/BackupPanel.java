package GUI.panel;


import SQL.Impl.ConfigDAO;
import SQL.data.Config;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import static util.SQLUtil.*;
public class BackupPanel extends WorkPanel {

    private final JButton bBackup = new JButton("备份");
    private final JFileChooser fcSaveDirection = new JFileChooser();
    private static final String fileName = "hutubill.sql";
    private final String cmd = String.format("/bin/mysqldump.exe\" -u%s -p%s %s > ", user, password, database);
    private void listenerInit() {
        bBackup.addActionListener(e -> {
            // 显示文件保存对话框
            int result = fcSaveDirection.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fcSaveDirection.getSelectedFile();
                //TODO backup by process cmd and have to know the Path of own mysql direction
                Config config = new ConfigDAO().get();
                String mysqlPath = config.getSQL_Path();
                try {
                    String run = String.format("cmd /c \"%s%s%s/%s",mysqlPath,cmd,selectedFile.getAbsolutePath(),fileName);
                    Process p = Runtime.getRuntime().exec(run);
                    int exitCode = 0;
                    try {
                        exitCode = p.waitFor();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if (exitCode == 0) {
                        JOptionPane.showMessageDialog(this,"备份成功");
                    } else {
                        JOptionPane.showMessageDialog(this,"备份失败");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    private void init() {
        fcSaveDirection.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // 仅允许选择文件夹
        add(bBackup, BorderLayout.NORTH);
    }

    private BackupPanel() {
        super();
        listenerInit();
        init();
    }

    public static BackupPanel instance = new BackupPanel();
}
