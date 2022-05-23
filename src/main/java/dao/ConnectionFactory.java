package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static DataSource ds;

    static {
        ComboPooledDataSource pool = new ComboPooledDataSource();
        try {
            pool.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Проверьте имя драйвера!", e);
        }
        pool.setJdbcUrl("jdbc:postgresql://localhost:5432/students_and_groups");
        pool.setUser("postgres");
        pool.setPassword("postgres");

        ds = pool;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close() throws Exception {
        DataSources.destroy(ds);
    }
}
