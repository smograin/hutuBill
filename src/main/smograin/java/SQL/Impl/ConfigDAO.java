package SQL.Impl;

import SQL.data.Config;
import util.SQLUtil;

import java.sql.*;

public class ConfigDAO{

    public Config get()
    {
        Connection c = SQLUtil.getConnection();
        Config config = null;
        try(Statement s = c.createStatement())
        {
            ResultSet rs = s.executeQuery("select * from config ");
            if(rs.next())
            {
                config = new Config();
            }else
            {
                s.execute("delete from config");
                s.execute("insert into config value(1,'Budget','500')");
                s.execute("insert into config value(2,'SQL_Path','null')");
                get();
            }
            do
            {
                if (rs.getString("Key_").equals("budget")) {
                    config.setBudget(Integer.parseInt(rs.getString("value")));
                }
                if (rs.getString("Key_").equals("SQL_Path"))
                    config.setSQL_Path(rs.getString("value"));
            }while(rs.next());

        }catch (SQLException|NullPointerException e)
        {
            e.printStackTrace();
        }
        SQLUtil.returnConnection(c);
        return config;
    }
    public void update(Config config)
    {
        Connection c = SQLUtil.getConnection();
        try(PreparedStatement ps = c.prepareStatement("update config set value = ? where id = ? "))
        {
            ps.setString(1,String.valueOf(config.getBudget()));
            ps.setInt(2,1);
            ps.execute();
            ps.setString(1,config.getSQL_Path());
            ps.setInt(2,2);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLUtil.returnConnection(c);
    }

}
