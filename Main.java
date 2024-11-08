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
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println(welcome);
            String border = "┌─────────────────────────────────────────────────────────────────────────────────────────┐";
            System.out.println(border);
            System.out.println(Extra.formatText("[1] Log in ", border.length()));
            System.out.println(Extra.formatText("[2] Sign up", border.length()));
            int action;                
            while (true) {
                System.out.print(Extra.centerTextWithInput("Action(1 or 2): ", border.length()));
                try {
                    action = input.nextInt();
                    input.nextLine();
        
                    if (action >= 1 && action <= 2) {
                        break; 
                    } else {
                        System.out.println(Extra.formatText("Invalid input. Please enter 1 or 2.", border.length()));
                    }
                } catch (InputMismatchException e) {
                    System.out.println(Extra.formatText("Invalid input. Please enter 1 or 2.", border.length()));
                    input.nextLine(); 
                }
            }
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────────┘");

            switch(action){
                case 1 -> {
                    System.out.print(Extra.centerTextWithInput("Enter Username: ", border.length()));
                    String username = input.nextLine();
                    System.out.print(Extra.centerTextWithInput("Enter Password: ", border.length()));
                    String password = input.nextLine();
                    if (PlayerManager.checkCredentials(username, password) == true){
                        System.out.println(Extra.formatText("Login successful for user: " + username, border.length()));
                        System.out.println(Extra.formatText("┌───────────────────────────────┐", border.length()));
                        System.out.println(Extra.formatText("PRESS ENTER TO CONTINUE..", border.length()));
                        System.out.println(Extra.formatText("└───────────────────────────────┘",border.length()));
                        input.nextLine();
                        Menu.first_menu(username);
                    }else{
                        System.out.println(Extra.formatText("Login Failed" + username, border.length()));
                        System.out.println(Extra.formatText("┌───────────────────────────────┐", border.length()));
                        System.out.println(Extra.formatText("PRESS ENTER TO RETURN..", border.length()));
                        System.out.println(Extra.formatText("└───────────────────────────────┘",border.length()));
                    }
                }

                case 2 -> {
                    System.out.print(Extra.centerTextWithInput("Enter Username: ", border.length()));
                    String username = input.nextLine();
                    System.out.print(Extra.centerTextWithInput("Enter Password: ", border.length()));
                    String password = input.nextLine();
                    PlayerManager.addPlayer(username, password);
                    System.out.println(Extra.formatText("Sign up successful " + username, border.length()));
                    System.out.println(Extra.formatText("┌───────────────────────────────┐", border.length()));
                    System.out.println(Extra.formatText("PRESS ENTER TO CONTINUE..", border.length()));
                    System.out.println(Extra.formatText("└───────────────────────────────┘",border.length()));
                    input.nextLine();
                    Menu.first_menu(username);
                    
                }
            }
        }
    } 

}
