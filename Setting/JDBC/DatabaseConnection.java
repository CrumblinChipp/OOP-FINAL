package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/city_simulator";
    private static final String USER = "root"; // change wtith your DB username
    private static final String PASSWORD = "mynewnameispasscode"; // change with your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
