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
                System.out.println(Extra.formatText("MENU\n"));
                System.out.println(Extra.formatText("[1] New Game"));
                System.out.println(Extra.formatText("[2] Records "));
                System.out.println(Extra.formatText("[3] Ranking "));
                System.out.println(Extra.formatText("[4] Setting "));
                System.out.println(Extra.formatText("[5] Exit    "));

                
                int action;
                while (true) {
                    System.out.print(Extra.centerTextWithInput(" Action(1-5): "));
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
                    case 2 -> {PlayerManager.showGameRecords(playerId); Extra.clearScreen();}
                    case 3 -> RankingManager.displayRankings();
                    case 4 -> {                
                        int settingAction;
                        while (true) {
                            System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
                            System.out.println(Extra.formatText("SETTING\n"));
                            System.out.println(Extra.formatText("[1] Change Username   "));
                            System.out.println(Extra.formatText("[2] Change Password   "));
                            System.out.println(Extra.formatText("[3] Clear Game Record "));
                            System.out.println(Extra.formatText("[4] Delete Account    "));
                            System.out.println(Extra.formatText("[5] Return            "));
                            System.out.print(Extra.centerTextWithInput(" Action(1-5): "));
                            try {
                                settingAction = scanner.nextInt();
                                scanner.nextLine();
        
                                if (settingAction >= 1 && settingAction <= 4) {
                                    break; 
                                } else {
                                    System.out.println(Extra.formatText("Invalid input. Please enter 1-5"));
                                }
                            } catch (InputMismatchException e) {
                                System.out.println(Extra.formatText("Invalid input. Please enter 1-5."));
                                scanner.nextLine(); 
                            }
                        }

                        switch (settingAction){
                            case 1 -> {
                                System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────┐"));
                                String newUsername;
                                while(true){
                                    System.out.print(Extra.centerTextWithInput("New Username: "));
                                    newUsername = scanner.nextLine();
                                    if (PlayerManager.isUsernameTaken(newUsername) == false){
                                        break;
                                    }
                                    
                                }
                                System.out.println(Extra.formatText("└───────────────────────────────────────────────────────┘"));

                                System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────┐"));
                                System.out.println(Extra.formatText("TYPE \"CONFIRM\" TO PROCEED WITH THE CHANGES"));
                                System.out.print(Extra.centerTextWithInput("Text: "));
                                String confirm = scanner.nextLine();
                                System.out.println(Extra.formatText("└───────────────────────────────────────────────────────┘"));
                                if ("CONFIRM".equals(confirm)){
                                    PlayerManager.changeUsername(playerId, newUsername);
                                    Extra.clearScreen();
                                }else {
                                    System.out.println(Extra.formatText("CHANGES ARE UNSAVED"));
                                    Extra.clearScreen();
                                }

                            }
                            case 2 -> {
                                
                                String oldPassword;
                                String newPassword;
                                while(true){
                                    System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────┐"));
                                    System.out.print(Extra.centerTextWithInput("Old Password: "));
                                    oldPassword = scanner.nextLine();
                                    System.out.print(Extra.centerTextWithInput("New Password: "));
                                    newPassword = scanner.nextLine();                                    
                                    if (PlayerManager.checkCredentials(user, oldPassword) == true){
                                        System.out.println(Extra.formatText("└───────────────────────────────────────────────────────┘"));

                                        System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────┐"));
                                        System.out.println(Extra.formatText("TYPE \"CONFIRM\" TO PROCEED WITH THE CHANGES"));
                                        System.out.print(Extra.centerTextWithInput("Text: "));
                                        String confirm = scanner.nextLine();
                                        System.out.println(Extra.formatText("└───────────────────────────────────────────────────────┘"));
                                        if ("CONFIRM".equals(confirm)){
                                            PlayerManager.changePassword(playerId, newPassword);
                                            Extra.clearScreen();
                                        }else {
                                            System.out.println(Extra.formatText("CHANGES ARE UNSAVED"));
                                            Extra.clearScreen();
                                        }
                                        break;
                                    }else{
                                        System.out.println(Extra.formatText("Old password was not correct"));
                                        Extra.clearScreen();
                                        break;
                                    }
                                }
                            }    
                            case 3 -> {
                                PlayerManager.showGameRecords(playerId);
                                System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────┐"));
                                System.out.println(Extra.formatText("! ! ! ! WARNING ! ! ! !"));
                                System.out.println(Extra.formatText("You are about to delete your game record!"));
                                System.out.println(Extra.formatText("[1] This will CLEAR the records of your game"));
                                System.out.println(Extra.formatText("[2] This will NOT affect your ranking       "));
                                System.out.println(Extra.formatText("└───────────────────────────────────────────────────────┘"));
                                System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────┐"));
                                System.out.println(Extra.formatText("TYPE \"CONFIRM\" TO PROCEED WITH THE CHANGES"));
                                System.out.print(Extra.centerTextWithInput("Text: "));
                                String confirm = scanner.nextLine();
                                System.out.println(Extra.formatText("└───────────────────────────────────────────────────────┘"));
                                if ("CONFIRM".equals(confirm)){
                                    PlayerManager.clearUserRecords(playerId);
                                    Extra.clearScreen();
                                }else {
                                    System.out.println(Extra.formatText("CHANGES ARE UNSAVED"));
                                    Extra.clearScreen();
                                }
                            }
                            case 4 ->{
                                System.out.println("Delete account");
                            }
                            case 5 -> Extra.clearScreen();
                        }
                    }
                    case 5 -> System.exit(action);
                }
            }
        }
    }
} 
    

