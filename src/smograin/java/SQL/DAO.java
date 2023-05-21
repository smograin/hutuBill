package SQL;

import util.ConnectionPool;
import util.SQLUtil;

import java.sql.Connection;
import java.util.List;

public interface DAO<T> {
    void add(T instance);
    //修改
    void update(T instance);
    //删除
    void delete(int index);
    //获取
    T get(int index);
    //查询
    List<T> list();
    //分页查询
    List<T> list(int start, int count);
}
