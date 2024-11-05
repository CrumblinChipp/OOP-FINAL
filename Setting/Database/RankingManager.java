package Database;

import GameSetup.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RankingManager {
        public static void updateRanking(int userId,double score) {
        String sql = """
            INSERT INTO rankings (user_id, best_score)
            VALUES (?, ?)
            ON CONFLICT(user_id) DO UPDATE SET best_score = MAX(best_score, ?);
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, score);
            pstmt.setDouble(3, score);
            pstmt.executeUpdate();
            System.out.println("Ranking updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

        public static void displayRankings(String user) {
        String sql = """
            SELECT p.username, r.best_score
            FROM rankings r
            JOIN players p ON r.user_id = p.user_id
            ORDER BY r.best_score DESC
            LIMIT 10;
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Top Players:");
            while (rs.next()) {
                System.out.println(rs.getString("username") + ": " + rs.getInt("best_score"));
            }
            System.out.println("\t\t\t   Press Enter to Return");
            System.in.read();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            Menu.first_menu(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
