package service;

import dao.StudentPoolDao;
import dao.api.IConnectionsDao;

public class StudentService {
    private static final StudentService instance = new StudentService();

    private final IConnectionsDao dao;

    public StudentService() {
        this.dao = new StudentPoolDao();
    }

    public static StudentService getInstance() {
        return instance;
    }
}
