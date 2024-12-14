package JDBC;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {
        String createPlayerTable = """
            CREATE TABLE IF NOT EXISTS players (
                user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                username VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(50) NOT NULL
            );
            """;

        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(createPlayerTable);
            System.out.println("Player table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void createGameRecordsTable() {
        String createGameRecordsTable = """
            CREATE TABLE IF NOT EXISTS game_records (
                game_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                user_id INTEGER NOT NULL,
                score INTEGER,
                date_played DATE,
                FOREIGN KEY (user_id) REFERENCES players (user_id),
                UNIQUE (user_id, game_id)
            );
            """;
    
        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(createGameRecordsTable);
            System.out.println("Game records table created successfully with unique player records.");
        } catch (SQLException e) {
            System.out.println("Error creating game records table: " + e.getMessage());
        }
    }
    
    public static void createRankingsTable() {
        String createRankingsTable = """
            CREATE TABLE IF NOT EXISTS rankings (
                ranking_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                user_id INTEGER NOT NULL,
                best_score INTEGER,
                FOREIGN KEY (user_id) REFERENCES players (user_id)
            );
            """;

        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(createRankingsTable);
            System.out.println("Rankings table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating rankings table: " + e.getMessage());
        }
    }
}
