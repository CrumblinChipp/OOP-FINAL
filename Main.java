import Database.PlayerManager;
import GameSetup.Extra;
import GameSetup.Menu;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        String welcome = """
                         \t__     _________ _______     __  __      __  __     __  ____    ________    __\r
                         \t\\ \\   / /   ___||   ____|   |  \\/  |    /  \\ \\ \\   / / / __ \\  |  ____  \\  |  |\r
                         \t \\ \\_/ /|  |__  |  |____    |      |   / /\\ \\ \\ \\_/ / | |  | | | |____| |  |  |\r
                         \t  \\   / |   __| |______ |   | \\  / |  / /__\\ \\ \\   /  | |  | | |   ___  /  |__|\r
                         \t   | |  |  |___  _____| |   | |\\/| | / /    \\ \\ | |   | |__| | |  |  \\  \\   __\r
                         \t   |_|  |______||_______|   |_|  |_|/_/      \\_\\|_|    \\____/  |__|   \\__\\ |__|\r
                         \t\t\t\t\t CITY SIMULATOR"""
        ;
        try (Scanner input = new Scanner(System.in)) {
            while (true){
                System.out.println(welcome);
                System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println(Extra.formatText("[1] Log in "));
                System.out.println(Extra.formatText("[2] Sign up"));
                int action;                
                while (true) {
                    System.out.print(Extra.centerTextWithInput("Action(1 or 2): "));
                    try {
                        action = input.nextInt();
                        input.nextLine();
            
                        if (action >= 1 && action <= 2) {
                            break; 
                        } else {
                            System.out.println(Extra.formatText("Invalid input. Please enter 1 or 2."));
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(Extra.formatText("Invalid input. Please enter 1 or 2."));
                        input.nextLine(); 
                    }
                }
                System.out.println();

                switch(action){
                    case 1 -> {
                        System.out.print(Extra.centerTextWithInput("Enter Username: "));
                        String username = input.nextLine();
                        System.out.println();
                        System.out.print(Extra.centerTextWithInput("Enter Password: "));
                        String password = input.nextLine();
                        System.out.println();
                        if (PlayerManager.checkCredentials(username, password) == true){
                            System.out.println(Extra.formatText("!! Login successful !! "));
                            Extra.clearScreen();
                            Menu.first_menu(username);
                        }else{
                            System.out.println(Extra.formatText("Login Failed"));
                            Extra.clearScreen();
                        }
                    }

                    case 2 -> {
                        System.out.println(Extra.formatText("┌───────────────────────────────────────────────────────────────┐"));
                        String username;
                        while(true){
                            System.out.print(Extra.centerTextWithInput("Enter Username: "));
                            username = input.nextLine();
                            System.out.println();
                            if (PlayerManager.isUsernameTaken(username) == false){
                                break;
                            }
                        }
                        System.out.print(Extra.centerTextWithInput("Enter Password: "));
                        String password = input.nextLine();
                        System.out.println();
                        PlayerManager.addPlayer(username, password);
                        System.out.println(Extra.formatText("!! Sign up successful !!" + username));
                        Extra.clearScreen();
                        Menu.first_menu(username);
                    }
                }
            }
        }
    } 

}
