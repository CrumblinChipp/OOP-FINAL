package GameSetup;
import java.util.ArrayList;
import java.util.List;

public class Scenario {
    static String border = "┌─────────────────────────────────────────────────────────────────────────────────────┐";
    protected static String centerText(String text) {
        int padding = (border.length() - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }
        centeredText.append(text);
        return centeredText.toString();
    }

// HEALTHCARE
public static void scenario1() {
    System.out.println(border);
    System.out.println(centerText("A pandemic has hit the city. Hospitals are overwhelmed,"));
    System.out.println(centerText("and citizens are demanding better healthcare services."));
    System.out.println();

    List<String> scenarioChoice1 = new ArrayList<>();
    scenarioChoice1.add("Build new hospitals");
    scenarioChoice1.add("Funding to existing hospitals");
    scenarioChoice1.add("Ignore the situation");

    System.out.println(centerText("What should we do?"));
    displayChoices(scenarioChoice1);
}

// INFRASTRUCTURE
public static void scenario2() {
    System.out.println(border);
    System.out.println(centerText("A natural disaster (flood/earthquake) has hit the city,"));
    System.out.println(centerText("causing extensive damage to infrastructure. The citizens"));
    System.out.println(centerText("expect immediate government intervention."));
    System.out.println();

    List<String> scenarioChoice2 = new ArrayList<>();
    scenarioChoice2.add("Allocate full disaster relief funds");
    scenarioChoice2.add("Provide limited funds for essential repairs");
    scenarioChoice2.add("Do nothing");

    System.out.println(centerText("What should we do?"));
    displayChoices(scenarioChoice2);
}

// ENVIRONMENT
public static void scenario3() {
    System.out.println(border);
    System.out.println(centerText("Factories are polluting the air and water, affecting the environment."));
    System.out.println(centerText("Citizens are demanding the government implement"));
    System.out.println(centerText("new environmental protection programs."));
    System.out.println();

    List<String> scenarioChoice3 = new ArrayList<>();
    scenarioChoice3.add("Launch a strict environmental protection program");
    scenarioChoice3.add("Impose some regulations");
    scenarioChoice3.add("Do nothing");

    System.out.println(centerText("What should we do?"));
    displayChoices(scenarioChoice3);
}

// MORALE
public static void scenario4() {
    System.out.println(border);
    System.out.println(centerText("Crime rates are rising across the city, leading to unrest and"));
    System.out.println(centerText("fear among citizens. There are calls for more"));
    System.out.println(centerText("policing and social programs."));
    System.out.println();

    List<String> scenarioChoice4 = new ArrayList<>();
    scenarioChoice4.add("Increase police funding and implement social programs");
    scenarioChoice4.add("Increase only police funding");
    scenarioChoice4.add("Do nothing");

    System.out.println(centerText("What should we do?"));
    displayChoices(scenarioChoice4);
}

// INNOVATION
public static void scenario5() {
    System.out.println(border);
    System.out.println(centerText("The economy is in decline. Unemployment is rising, and businesses are closing."));
    System.out.println(centerText("Citizens demand an economic stimulus package."));
    System.out.println();

    List<String> scenarioChoice5 = new ArrayList<>();
    scenarioChoice5.add("Implement a large stimulus package");
    scenarioChoice5.add("Offer tax breaks to small businesses");
    scenarioChoice5.add("Do nothing");

    System.out.println(centerText("What should we do?"));
    displayChoices(scenarioChoice5);
}

// EDUCATION
public static void scenario6() {
    System.out.println(border);
    System.out.println(centerText("The city's education system is outdated, and teachers are demanding"));
    System.out.println(centerText("reforms to ensure better education for children."));
    System.out.println();

    List<String> scenarioChoice6 = new ArrayList<>();
    scenarioChoice6.add("Completely overhaul the education system");
    scenarioChoice6.add("Implement moderate reforms");
    scenarioChoice6.add("Ignore demands");

    System.out.println(centerText("What should we do?"));
    displayChoices(scenarioChoice6);
}

// Helper method to display choices in a formatted way
public static void displayChoices(List<String> choices) {
    for (int i = 0; i < choices.size(); i++) {
        System.out.println("\t\t" + (i + 1) + ". " + choices.get(i) + "\n");
    }
}

}
