package Database;

import GameSetup.Extra;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

            // Collect results into a list for flexible display
            List<String> topRankings = new ArrayList<>();

            while (rs.next()) {
                String username = rs.getString("username");
                int bestScore = rs.getInt("best_score");
                topRankings.add(String.format("%-4d       \t\t\t%-10s", bestScore, username));
            }

            // Print the header
            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("                                    Rankings                                      ");
            System.out.println("\n\t\t\tScore          |               Player               ");
            System.out.println("     ________________________________________________________________________");

            // Display top 3 players
            for (int i = 0; i < topRankings.size() && i < 3; i++) {
                System.out.printf("\tTop %-2d:          %s%n", i + 1, topRankings.get(i));
            }

            // Display the next 7 players if available
            for (int i = 3; i < topRankings.size() && i < 10; i++) {
                System.out.printf("\t [%-2d]            %s%n", i + 1, topRankings.get(i));
            }

            // Fill in blanks if fewer than 10 players exist
            for (int i = topRankings.size(); i < 10; i++) {
                System.out.printf("\t [%-2d]         %-4s            %-10s%n", i + 1, "", "");
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
