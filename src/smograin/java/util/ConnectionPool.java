package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool
{
    private static String database_driver = "com.mysql.jdbc.Driver";
    private static String database_URL = "jdbc:mysql://127.0.0.1:3306/";
    private static String database_name = "hutubill";
    private static String database_character = "UTF-8";
    private static String database_user = "root";
    private static String database_password = "admin";
    int size = 3;
    Stack<Connection> pool = new Stack<>();
    protected void setSize(int size) throws SQLException {
        for (int i = 0; i < size - this.size; i++) {
            pool.push(DriverManager.getConnection(database_URL+database_name+"?characterEncoding="+
                    database_character,database_user,database_password));
        }
        this.size = size;
    }
    private void init() throws SQLException {
        try {
            Class.forName(database_driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < size; i++) {
            pool.push(DriverManager.getConnection(database_URL+database_name+"?characterEncoding="+
                    database_character,database_user,database_password));
        }
    }

    public ConnectionPool() throws SQLException {
        init();
    }
    public synchronized Connection getConnection(){
        while (pool.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return pool.pop();
    }

    public synchronized void returnConnection(Connection c)
    {
        pool.push(c);
        notifyAll();
    }
}