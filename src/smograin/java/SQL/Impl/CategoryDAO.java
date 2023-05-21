package SQL.Impl;

import SQL.DAO;
import SQL.data.Category;
import util.SQLUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DAO<Category> {
    @Override
    public void add(Category instance) {
        String insert = "insert into category values (null , ? , ?)";
        Connection c = SQLUtil.getConnection();
        try(PreparedStatement ps = c.prepareStatement(insert))
        {
            ps.setString(1,instance.getName());
            ps.setInt(2,instance.getTimes());
            ps.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        SQLUtil.returnConnection(c);

    }

    @Override
    public void update(Category instance) {
        String update = "update category set name = ? , times = ? where id = ?";
        Connection c = SQLUtil.getConnection();
        try(PreparedStatement ps = c.prepareStatement(update))
        {
            ps.setString(1,instance.getName());
            ps.setInt(2,instance.getTimes());
            ps.setInt(3,instance.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally
        {
            SQLUtil.returnConnection(c);
        }
    }

    @Override
    public void delete(int index) {
        String delete = "delete from category where id = " + index;
        Connection c = SQLUtil.getConnection();
        try(Statement s = c.createStatement())
        {
            s.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            SQLUtil.returnConnection(c);
        }
    }

    public void delete (Category c)
    {
        delete(c.getId());
    }


    @Override
    public Category get(int index) {
        String select = "select form category where id = " + index;
        Connection c = SQLUtil.getConnection();
        Category category = null;
        try(Statement s = c.createStatement())
        {
            ResultSet rs = s.executeQuery(select);
            while(rs.next())
            {
                 category = new Category(rs.getInt(1),rs.getString(2));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            SQLUtil.returnConnection(c);
        }
        return category;
    }

    @Override
    public List<Category> list() {
        String select = "select * from category";
        Connection c = SQLUtil.getConnection();
        List<Category> CategoryList = new ArrayList<>();
        try(Statement s = c.createStatement())
        {
            ResultSet rs = s.executeQuery(select);
            while(rs.next())
            {
                CategoryList.add(new Category(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            SQLUtil.returnConnection(c);
        }
        return CategoryList;

    }

    @Override
    public List<Category> list(int start, int count) {
        return null;
    }
}
