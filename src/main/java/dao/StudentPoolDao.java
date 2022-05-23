package dao;

import dao.api.IConnectionsDao;
import dto.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentPoolDao implements AutoCloseable, IConnectionsDao {

    private static final StudentPoolDao instance = new StudentPoolDao();

    public StudentPoolDao() {
    }

    public List<Student> getAll(){
        List<Student> students = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    student_id,\n" +
                             "    student_name,\n" +
                             "    student_age,\n" +
                             "    score,\n" +
                             "    olympic_gamer\n" +
                             "FROM\n" +
                             "    students\n;"
             );
        ) {
            while (resultSet.next()){
                students.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public Student get(int id, String tableName){
        try (Connection connection = dao.ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT\n" +
                             "    student_id,\n" +
                             "    student_name,\n" +
                             "    student_age,\n" +
                             "    score,\n" +
                             "    olympic_gamer\n" +
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

    public void add(Student student, String tableName){
        try (Connection connection = dao.ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO " + tableName + "(" +
                             "      student_id,\n" +
                             "      student_name,\n" +
                             "      student_age,\n" +
                             "      score,\n" +
                             "      olympic_gamer)\n" +
                             "VALUES (\n" +
                             student.getId() + ",\n" +
                             "'" + student.getName() + "',\n" +
                             student.getAge() + ",\n" +
                             student.getScore() + ",\n" +
                             student.isOlympicGamer() + ");"
             )
        ) {} catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String name){
        try (Connection connection = dao.ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "CREATE TABLE " + name +
                             " (" +
                             "student_id INTEGER PRIMARY KEY,\n" +
                             "student_name VARCHAR(20),\n" +
                             "student_age INT,\n" +
                             "score DECIMAL(2,1),\n" +
                             "olympic_gamer BOOLEAN\n" +
                             ");"
             );
        ) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Student map(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("student_id"),
                rs.getString("student_name"),
                rs.getInt("student_age"),
                rs.getDouble("score"),
                rs.getBoolean("olympic_gamer")
        );
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }
}
