package GameSetup;

import Database.PlayerManager;
import Database.RankingManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void first_menu(String user) {
        int playerId = PlayerManager.getUserId(user);
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
                    case 2 -> PlayerManager.showGameRecords(playerId);
                    case 3 -> RankingManager.displayRankings();
                    case 4 -> {                
                        int settingAction;
                        while (true) {
                            System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
                            System.out.println(Extra.formatText("SETTING\n"));
                            System.out.println(Extra.formatText("[1] Change Username   "));
                            System.out.println(Extra.formatText("[2] Change Password   "));
                            System.out.println(Extra.formatText("[3] Clear Game Record "));
                            System.out.println(Extra.formatText("[4] Return            "));
                            System.out.print(Extra.centerTextWithInput("Action(1-4): "));
                            try {
                                settingAction = scanner.nextInt();
                                scanner.nextLine();
        
                                if (settingAction >= 1 && settingAction <= 4) {
                                    break; 
                                } else {
                                    System.out.println(Extra.formatText("Invalid input. Please enter 1-4."));
                                }
                            } catch (InputMismatchException e) {
                                System.out.println(Extra.formatText("Invalid input. Please enter 1-4."));
                                scanner.nextLine(); 
                            }
                        }

                        switch (settingAction){
                            case 1 -> {
                                System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────┐"));
                                String newUsername;
                                while(true){
                                    System.out.println(Extra.centerTextWithInput("New Username: "));
                                    newUsername = scanner.nextLine();
                                    if (PlayerManager.isUsernameTaken(newUsername) == false){
                                        break;
                                    }
                                    
                                }
                                System.out.println(Extra.formatText("└───────────────────────────────────────────────────────┘"));

                                System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────┐"));
                                System.out.println(Extra.formatText("ENTER \"CONFIRM\" TO PROCEED WITH THE CHANGES"));
                                System.out.print(Extra.centerTextWithInput("Text: "));
                                String confirm = scanner.nextLine();
                                System.out.println(Extra.formatText("└───────────────────────────────────────────────────────┘"));
                                if ("CONFIRM".equals(confirm)){
                                    PlayerManager.changeUsername(playerId, newUsername);
                                }

                                }
                            case 2 -> {
                                System.out.println("Change password");
                            }    
                            case 3 -> {
                                System.out.println("Clear record");
                            }
                            }

                        }
                    case 5 -> System.exit(action);
                }
            }
        }
    }
} 
    

