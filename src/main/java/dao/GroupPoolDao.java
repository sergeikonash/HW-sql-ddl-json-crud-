package dao;

import dao.api.IConnectionsDao;
import dto.Group;
import dto.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupPoolDao implements AutoCloseable, IConnectionsDao {

    private static final GroupPoolDao instance = new GroupPoolDao();

    public GroupPoolDao() {
    }

    public List<Group> getAll(){
        List<Group> groups = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    group_id,\n" +
                             "    group_number\n" +
                             "FROM\n" +
                             "    groups\n;"
             );
        ) {
            while (resultSet.next()){
                groups.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return groups;
    }

    public Group get(int id, String tableName){
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    group_id,\n" +
                             "    group_number\n" +
                             "FROM\n" +
                                  tableName + "\n" +
                             "WHERE group_id = " + id + ";"
             );
        ) {
            while (resultSet.next()){
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void add(Group group, String tableName){
        try (Connection connection = dao.ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();) {
             statement.executeUpdate(
                     "INSERT INTO " + tableName + "(" +
                             "    group_id,\n" +
                             "    group_number)\n" +
                             "VALUES (\n" +
                             group.getId() + ",\n" +
                             "'" + group.getNumber() + "');"
             );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String name){
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "CREATE TABLE " + name +
                             " (" +
                             "group_id INTEGER PRIMARY KEY,\n" +
                             "group_number VARCHAR(20)\n" +
                             ");"
             );
        ) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Group map(ResultSet rs) throws SQLException {
        return new Group(
                rs.getInt("group_id"),
                rs.getString("group_number")
        );
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
