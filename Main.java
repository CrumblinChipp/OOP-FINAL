import Database.PlayerManager;
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
            String borderTop = "┌─────────────────────────────────────────────────────────────────────────────────────────┐";
            System.out.println(borderTop);
            System.out.println(centerText("[1] Log in ", borderTop.length()));
            System.out.println(centerText("[2] Sign up", borderTop.length()));
            int action;                
            while (true) {
                System.out.print(centerText("Action(1 or 2): ", borderTop.length()));
                try {
                    action = input.nextInt();
                    input.nextLine();
        
                    if (action >= 1 && action <= 2) {
                        break; 
                    } else {
                        System.out.print(centerText("Invalid input. Please enter 1 or 2.", borderTop.length()));
                    }
                } catch (InputMismatchException e) {
                    System.out.println(centerText("Invalid input. Please enter 1 or 2.", borderTop.length()));
                    input.nextLine(); 
                }
            }
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────────┘");

            switch(action){
                case 1 -> {
                    System.out.print(centerText("Enter Username: ", borderTop.length()));
                    String username = input.nextLine();
                    System.out.print(centerText("Enter Password: ", borderTop.length()));
                    String password = input.nextLine();
                    if (PlayerManager.checkCredentials(username, password) == true){
                        System.out.println(centerText("Login successful for user: " + username, borderTop.length()));
                        System.out.println(centerText("┌───────────────────────────────┐", borderTop.length()));
                        System.out.println(centerText("PRESS ENTER TO CONTINUE..", borderTop.length()));
                        System.out.println(centerText("└───────────────────────────────┘",borderTop.length()));
                        Menu.first_menu(username);
                    }else{
                        System.out.println(centerText("Login Failed" + username, borderTop.length()));
                        System.out.println(centerText("┌───────────────────────────────┐", borderTop.length()));
                        System.out.println(centerText("PRESS ENTER TO RETURN..", borderTop.length()));
                        System.out.println(centerText("└───────────────────────────────┘",borderTop.length()));
                    }
                }

                case 2 -> {
                    System.out.print(centerText("Enter Username: ", borderTop.length()));
                    String username = input.nextLine();
                    System.out.print(centerText("Enter Password: ", borderTop.length()));
                    String password = input.nextLine();
                    PlayerManager.addPlayer(username, password);
                    System.out.println(centerText("Sign up successful " + username, borderTop.length()));
                    System.out.println(centerText("┌───────────────────────────────┐", borderTop.length()));
                    System.out.println(centerText("PRESS ENTER TO CONTINUE..", borderTop.length()));
                    System.out.println(centerText("└───────────────────────────────┘",borderTop.length()));
                    Menu.first_menu(username);
                    
                }
            }
        }
    } 

    protected static String centerText(String text, int border) {
        int padding = (border - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }
        centeredText.append(text);
        return centeredText.toString();
    }
}
