package Database;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RankingManager {
    public static void updateRanking(int userId, double score) {
        BigDecimal roundedScore = new BigDecimal(score).setScale(2, RoundingMode.HALF_UP);

        String sql = """
            INSERT INTO rankings (user_id, best_score)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE best_score = GREATEST(best_score, ?);
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setBigDecimal(2, roundedScore);
            pstmt.setBigDecimal(3, roundedScore);
            pstmt.executeUpdate();
            
            System.out.println("Ranking updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating ranking: " + e.getMessage());
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
