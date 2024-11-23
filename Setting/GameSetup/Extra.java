package GameSetup;

import java.io.IOException;

public class Extra {

    public static void clearScreen() {
        System.out.println(formatText("┌───────────────────────────────┐"));
        System.out.println(formatText("PRESS ENTER TO CONTINUE.."));
        System.out.println(formatText("└───────────────────────────────┘"));

        try {
            System.in.read();
        } catch (IOException e) {
        }
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void centerText(String message) {
        System.out.println("┌" + "─".repeat(82) + "┐");
    
        int padding = 2;
        int maxLineLength = 82 - padding * 2;
    
        String[] words = message.split(" ");
        StringBuilder line = new StringBuilder();
    
        for (String word : words) {
            if (line.length() + word.length() + 1 > maxLineLength) {
                System.out.println(formatText(line.toString()));
                line = new StringBuilder();
            }
            if (line.length() > 0) line.append(" ");
            line.append(word);
        }
    
        if (line.length() > 0) {
            System.out.println(formatText(line.toString()));
        }
    }
    
    public static String formatText(String text) {
        int padding = (82 - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
    
        centeredText.append(" ".repeat(padding));
    
        centeredText.append(text);
    
        centeredText.append(" ".repeat(82 - centeredText.length()));
    
        return centeredText.toString();
    }
    
    public static String centerTextWithInput(String text) {
        int padding = (82 - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }
        centeredText.append(text);
        return centeredText.toString();
    }

}
