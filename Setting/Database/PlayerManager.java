package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
            
            if (resultSet.next()) {
                System.out.println("Login successful for user: " + username);
                return true;
            } else {
                System.out.println("Invalid credentials for user: " + username);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error during credential check: " + e.getMessage());
            return false;
        }
    }

    public static int getUserIdByUsername(String username) {
        String query = "SELECT user_id FROM players WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            // Set the username parameter for the prepared statement
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

    public static void showGameRecordsByUserId(int userId) {
        String query = "SELECT score, date_played FROM game_records WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the userID parameter
            pstmt.setInt(1, userId);

            // Execute the query and get results
            ResultSet rs = pstmt.executeQuery();

            // Display header
            System.out.println("\t\t\t┌───────────────────────────────┐");
            System.out.println("\t\t\t│        Game Records           │");
            System.out.println("\t\t\t├─────────────┬─────────────────┤");
            System.out.println("\t\t\t│    Date     │      Score      │");
            System.out.println("\t\t\t├─────────────┼─────────────────┤");

            // Process and display results
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                String datePlayed = rs.getString("date_played");
                int score = rs.getInt("score");

                // Format each row
                System.out.printf("\t\t\t│ %-11s │ %-15d │%n", datePlayed, score);
            }

            if (!hasRecords) {
                System.out.println("\t\t\t│      No records found.        │");
            }

            // Display footer
            System.out.println("\t\t\t└─────────────┴─────────────────┘");
            System.out.println("\t\t\t   Press Enter to Return");

            // Wait for Enter to return
            System.in.read();

        } catch (SQLException e) {
            System.out.println("Error retrieving game records.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    

}
