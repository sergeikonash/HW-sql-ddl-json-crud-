package main;

import dao.CrossTablePoolDao;
import dao.GroupPoolDao;
import dao.StudentPoolDao;
import dto.Group;
import dto.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main1 {
    public static void main(String[] args) {

        StudentPoolDao studentPoolDao = new StudentPoolDao();
        GroupPoolDao groupPoolDao = new GroupPoolDao();
        CrossTablePoolDao crossTablePoolDao = new CrossTablePoolDao();

        try {
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "postgres");
            Statement statement = c.createStatement();
            statement.executeUpdate("CREATE DATABASE students_and_groups");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            studentPoolDao.createTable("students");
            Student student = new Student(11, "Sergej", 17, 8.2, true);
            Student student1 = new Student(12, "Sergei", 16, 8.3, false);
            studentPoolDao.add(student, "students");
            studentPoolDao.add(student1, "students");
        } catch (RuntimeException e) {
            e.getMessage();
        }

        try {
            groupPoolDao.createTable("groups");
            Group group = new Group(1, "101g");
            Group group1 = new Group(2, "102g");
            groupPoolDao.add(group, "groups");
            groupPoolDao.add(group1, "groups");
        } catch (RuntimeException e) {
            e.getMessage();
        }

        try {
            crossTablePoolDao.createTable("cross_table");
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }
}
