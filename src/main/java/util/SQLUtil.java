package util;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLUtil {
    public static String user = "root";
    public static String password = "admin";
    public static String database = "hutubill";
    private static ConnectionPool pool;

    static {
        try {
            pool = new ConnectionPool();
        } catch (SQLException e) {
            System.out.println("your database has a little problem");
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return pool.getConnection();
    }
    public static void returnConnection(Connection c)
    {
        pool.returnConnection(c);
    }
}





