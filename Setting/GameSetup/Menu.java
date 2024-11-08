package GameSetup;

import Database.PlayerManager;
import Database.RankingManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void first_menu(String user) {
        int playerId = PlayerManager.getUserIdByUsername(user);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                
                System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println(Extra.formatText("[1] New Game"));
                System.out.println(Extra.formatText("[2] Records "));
                System.out.println(Extra.formatText("[3] Ranking "));
                System.out.println(Extra.formatText("[4] Setting "));
                System.out.println(Extra.formatText("[5] Exit    "));

                
                int action;
                while (true) {
                    System.out.print(Extra.centerTextWithInput("Action(1-5): "));
                    try {
                        action = scanner.nextInt();
                        scanner.nextLine();
   
                        if (action >= 1 && action <= 5) {
                            break; 
                        } else {
                            System.out.println(Extra.formatText("Invalid input. Please enter 1-5."));
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(Extra.formatText("Invalid input. Please enter 1-5."));
                        scanner.nextLine(); 
                    }
                }
                
                switch (action) {
                    case 1 -> Game.GameRun(user, playerId);
                    case 2 -> PlayerManager.showGameRecordsByUserId(playerId, user);
                    case 3 -> RankingManager.displayRankings(user);
                    case 4 -> System.out.println("\n\t\t\t\t\tSetting functionality is not implemented yet.");
                    case 5 -> System.exit(action);
                }
            }
        }
    }
} 
    

