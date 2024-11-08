package Database;
import GameSetup.Extra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    public static void addPlayer(String username, String password) {
        String sql = "INSERT INTO players (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

        public static boolean checkCredentials(String username, String password) {
        String query = "SELECT * FROM players WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username); 
            pstmt.setString(2, password);
            
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error during credential check: " + e.getMessage());
            return false;
        }
    }

    public static int getUserIdByUsername(String username) {
        String query = "SELECT user_id FROM players WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            
            // Execute the query and process the result
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            } else {
                System.out.println("No user found with username: " + username);
                return -1;  // Return -1 if user not found
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user ID: " + e.getMessage());
            return -1;
        }
    }

    public static void recordGame(int userId, double score) {
        String insertGameRecord = """
            INSERT INTO game_records (user_id, score, date_played)
            VALUES (?, ?, ?);
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertGameRecord)) {
            
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, score);
            pstmt.setString(3,LocalDate.now().toString());
            int rowsInserted = pstmt.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("Game record added successfully for user ID: " + userId);
            } else {
                System.out.println("Failed to add game record for user ID: " + userId);
            }
            
        } catch (SQLException e) {
            System.out.println("Error recording game: " + e.getMessage());
        }
    }

    public static void showGameRecordsByUserId(int userId, String user) {
        // Subquery to get the best score and its date
        String bestScoreQuery = """
            SELECT score, date_played
            FROM game_records
            WHERE user_id = ? AND score = (SELECT MAX(score) FROM game_records WHERE user_id = ?)
            LIMIT 1
        """;
    
        // Query to fetch the 10 most recent records
        String recentRecordsQuery = """
            SELECT score, date_played
            FROM game_records
            WHERE user_id = ?
            ORDER BY date_played DESC
            LIMIT 10
        """;
    
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            // Get best score and its date
            String bestScoreDate = "";
            int bestScore = 0;
            try (PreparedStatement bestScoreStmt = conn.prepareStatement(bestScoreQuery)) {
                bestScoreStmt.setInt(1, userId);
                bestScoreStmt.setInt(2, userId);
                ResultSet bestScoreRs = bestScoreStmt.executeQuery();
                if (bestScoreRs.next()) {
                    bestScore = bestScoreRs.getInt("score");
                    bestScoreDate = bestScoreRs.getString("date_played");
                }
            }
    
            // Get the 10 most recent game records
            List<String> records = new ArrayList<>();
            try (PreparedStatement recentStmt = conn.prepareStatement(recentRecordsQuery)) {
                recentStmt.setInt(1, userId);
                ResultSet recentRs = recentStmt.executeQuery();
    
                while (recentRs.next()) {
                    int score = recentRs.getInt("score");
                    String datePlayed = recentRs.getString("date_played");
                    records.add(String.format("[%-2d]                 %-4d                           %-10s", records.size() + 1, score, datePlayed));
                }
            }
    
            // Display formatted output
            System.out.println("┌──────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("                                    Game Records                                  ");
            System.out.println("\n\t\t\tScore            |          \tDate Played         \n")  ;
            System.out.printf("Best Record:    \t %-9d                      %-20s%n", bestScore, bestScoreDate);
            System.out.println("     ________________________________________________________________________");

            for (int i = 0; i < 10; i++) {
                if (i < records.size()) {
                    System.out.println("    " + records.get(i));
                } else {
                    System.out.printf("    [%-2d] %-20s       %-30s%n", i + 1, "", "");
                }
            }
    
            System.out.println("└──────────────────────────────────────────────────────────────────────────────────┘");
            Extra.clearScreen();
    
        } catch (SQLException e) {
            System.out.println("Error retrieving game records.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    
}
