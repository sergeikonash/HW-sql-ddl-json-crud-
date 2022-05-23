package dao;

import dao.api.IConnectionsDao;
import dto.CrossTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrossTablePoolDao implements AutoCloseable, IConnectionsDao {

    private static final CrossTablePoolDao instance = new CrossTablePoolDao();

    public CrossTablePoolDao() {
    }

    public List<CrossTable> getAll(){
        List<CrossTable> groups = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    student_id,\n" +
                             "    group_id\n" +
                             "FROM\n" +
                             "    cross_table\n;"
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

    public CrossTable get(int id, String tableName){
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    student_id,\n" +
                             "    group_id\n" +
                             "FROM\n" +
                                  tableName + "\n" +
                             "WHERE student_id = " + id + ";"
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

    public void createTable(String name){
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "CREATE TABLE " + name +
                             " (" +
                             "student_id INTEGER NOT NULL,\n" +
                             "group_id INTEGER NOT NULL," +
                             "FOREIGN KEY (student_id)  REFERENCES students (student_id)," +
                             "FOREIGN KEY (group_id)  REFERENCES groups (group_id)" +
                             ");"
             );
        ) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private CrossTable map(ResultSet rs) throws SQLException {
        return new CrossTable(
                rs.getLong("student_id"),
                rs.getLong("group_id")
        );
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
