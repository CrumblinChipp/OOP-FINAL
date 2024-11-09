package Database;

import GameSetup.Extra;
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

    public static void displayRankings() {
        String sql = """
            SELECT p.username, MAX(r.best_score) AS best_score
            FROM rankings r
            JOIN players p ON r.user_id = p.user_id
            GROUP BY p.username
            ORDER BY best_score DESC
            LIMIT 10;
        """;
    
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            // Print the header
            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("                                    Rankings                                      ");
            System.out.println("\n\t\t\tScore          |               Player               ");
            System.out.println("     ________________________________________________________________________");
    
            // Track ranking
            int rank = 1;
    
            // Display top 3 players
            while (rs.next() && rank <= 3) {
                String username = rs.getString("username");
                int bestScore = rs.getInt("best_score");
                System.out.printf("\tTop %-2d:          %-4d       \t\t\t%-10s%n", rank, bestScore, username);
                rank++;
            }
    
            // Fill in the rest up to 10 players, ensuring no duplicates
            while (rs.next() && rank <= 10) {
                String username = rs.getString("username");
                int bestScore = rs.getInt("best_score");
                System.out.printf("\t [%-2d]         %-4d          \t\t\t%-10s%n", rank, bestScore, username);
                rank++;
            }
    
            // If fewer than 10 players exist, fill in the blanks
            while (rank <= 10) {
                System.out.printf("\t [%-2d]         %-4s            %-10s%n", rank, "", "");
                rank++;
            }
    
            System.out.println("└──────────────────────────────────────────────────────────────────────────────────┘");
            Extra.clearScreen();
    
        } catch (SQLException e) {
            System.out.println("Error retrieving rankings.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
}
