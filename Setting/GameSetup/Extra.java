package GameSetup;

import java.io.IOException;

public class Extra {

    public static void clearScreen() {
        String border = "┌────────────────────────────────────────────────────────────────────────────────────┐";
        System.out.println(formatText("┌───────────────────────────────┐", border.length()));
        System.out.println(formatText("PRESS ENTER TO CONTINUE..", border.length()));
        System.out.println(formatText("└───────────────────────────────┘",border.length()));

        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void centerText(String message, int boxWidth) {
        System.out.println("┌" + "─".repeat(boxWidth) + "┐");
    
        int padding = 2; 
        int maxLineLength = boxWidth - padding * 2;
    
        String[] words = message.split(" ");
        StringBuilder line = new StringBuilder();
    
        for (String word : words) {
            if (line.length() + word.length() + 1 > maxLineLength) {
                System.out.println(formatText(line.toString(), boxWidth));
                line = new StringBuilder();
            }
            if (line.length() > 0) line.append(" ");
            line.append(word);
        }
    
        if (line.length() > 0) {
            System.out.println(formatText(line.toString(), boxWidth));
        }
    }
    
    public static String formatText(String text, int lineWidth) {
        int padding = (lineWidth - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
    
        centeredText.append(" ".repeat(padding));
    
        centeredText.append(text);
    
        centeredText.append(" ".repeat(lineWidth - centeredText.length()));
    
        return centeredText.toString();
    }
    
    public static String centerTextWithInput(String text, int border) {
        int padding = (border - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }
        centeredText.append(text);
        return centeredText.toString();
    }
}
