package dao.api;

import dto.Student;

import java.util.List;

public interface IConnectionsDao<T> {
    public List<T> getAll();
    public T get(int id, String tableName);
    public void createTable(String name);
}
