package GUI.panel;

import java.io.IOException;

public class MysqlBackup {
    public static void main(String[] args) throws IOException {
        String user = "root"; // 数据库用户名
        String password = "password"; // 数据库密码
        String database = "database"; // 要备份的数据库名
        String backupPath = "/backup/database.sql"; // 备份文件存储路径

        // 构造备份命令，调用 mysqldump 进行备份
        String cmd = String.format("mysqldump -u%s -p%s %s > %s", user, password, database, backupPath);
        Process process = Runtime.getRuntime().exec(cmd);

        // 等待备份完成

    }
}