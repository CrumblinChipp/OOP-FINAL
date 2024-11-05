package GameSetup;

import Database.PlayerManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void first_menu(String user){
        int playerId = PlayerManager.getUserIdByUsername(user);
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(user);
            System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("\t\t\t\t\t[1] New Game");
            System.out.println("\t\t\t\t\t[2] Personal Highscore");
            System.out.println("\t\t\t\t\t[3] Ranking");
            System.out.println("\t\t\t\t\t[4] Setting");
            
            int action;
            while (true) {
                System.out.print("\t\t\tAction(1-4):");
                try {
                    action = scanner.nextInt();
                    scanner.nextLine(); // consume newline
        
                    // Validate input
                    if (action >= 1 && action <= 3) {
                        break; // Exit the loop if valid choice
                    } else {
                        System.out.println("\t\t\tInvalid input. Please enter 1-4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\t\t\tInvalid input. Please enter a number (1-4).");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            switch (action){
                case 1 -> {
                    Game.GameRun(user, playerId);
                }
                case 2 -> {
                    PlayerManager.showGameRecordsByUserId(playerId);
                }
                case 3 ->{
                    System.out.println("\n\t\t\t\t\tRanking functionality is not implemented yet.");
                }
                case 4 -> {
                    System.out.println("\n\t\t\t\t\tSetting functionality is not implemented yet.");
                }

            }
        }

    }
}
