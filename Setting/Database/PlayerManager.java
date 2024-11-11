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

    public static int getUserId(String username) {
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
            
        } catch (SQLException e) {
            System.out.println("Error recording game: " + e.getMessage());
        }
    }

    public static void showGameRecords(int userId) {
        String bestScoreQuery = """
            SELECT score, date_played
            FROM game_records
            WHERE user_id = ? AND score = (SELECT MAX(score) FROM game_records WHERE user_id = ?)
            LIMIT 1
        """;
    
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
    
        } catch (SQLException e) {
            System.out.println("Error retrieving game records.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    
    public static void changeUsername(int userId, String newUsername) {

        String sql = "UPDATE players SET username = ? WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newUsername);
            pstmt.setInt(2, userId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(Extra.formatText("Username updated successfully."));
            } else {
                System.out.println("User ID not found. Username update failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating username: " + e.getMessage());
        }
    }

    public static boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(*) AS count FROM players WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt("count") > 0) {

                System.out.println(Extra.formatText("Username is already taken. Please choose a different username."));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error checking username availability: " + e.getMessage());
        }
        return false;
    }

    public static void changePassword(int userId, String newPassword) {
        
        String sql = "UPDATE players SET password = ? WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);
            pstmt.setInt(2, userId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(Extra.formatText("Password updated successfully."));
            } else {
                System.out.println("User ID not found. Password update failed.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }

    public static void clearUserRecords(int userId) {
        String sql = "DELETE FROM game_records WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(Extra.formatText("Your game records are cleared successfully."));
            } else {
                System.out.println("No records found for the specified user.");
            }

        } catch (SQLException e) {
            System.out.println("Error clearing user records: " + e.getMessage());
        }
    }

    public static void deleteAccount(int userId) {
        String deleteGameRecordsSql = "DELETE FROM game_records WHERE user_id = ?";
        String deleteRankingSql = "DELETE FROM rankings WHERE user_id = ?";
        String deletePlayerSql = "DELETE FROM players WHERE user_id = ?";

        // Declare connection outside to access it in catch block if needed
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);  

            // Delete game records
            try (PreparedStatement pstmtGameRecords = conn.prepareStatement(deleteGameRecordsSql)) {
                pstmtGameRecords.setInt(1, userId);
                pstmtGameRecords.executeUpdate();
                System.out.println("User's game records deleted successfully.");
            }

            // Delete ranking entry
            try (PreparedStatement pstmtRanking = conn.prepareStatement(deleteRankingSql)) {
                pstmtRanking.setInt(1, userId);
                pstmtRanking.executeUpdate();
                System.out.println("User's ranking entry deleted successfully.");
            }

            // Delete player account
            try (PreparedStatement pstmtPlayer = conn.prepareStatement(deletePlayerSql)) {
                pstmtPlayer.setInt(1, userId);
                int rowsAffected = pstmtPlayer.executeUpdate();
                
                if (rowsAffected > 0) {
                    System.out.println("User account deleted successfully.");
                } else {
                    System.out.println("User ID not found. Account deletion failed.");
                }
            }

            // Commit transaction if all deletions succeeded
            conn.commit();

        } catch (SQLException e) {
            System.out.println("Error deleting user account: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();  // Rollback transaction on error
                    System.out.println("Transaction rolled back.");
                }
            } catch (SQLException rollbackException) {
                System.out.println("Error during rollback: " + rollbackException.getMessage());
            }
        } finally {
            // Ensure connection is closed after operation
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeException) {
                    System.out.println("Error closing connection: " + closeException.getMessage());
                }
            }
        }
    }
}
