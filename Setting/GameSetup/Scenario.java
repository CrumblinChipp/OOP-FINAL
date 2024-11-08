package GameSetup;
import java.util.ArrayList;
import java.util.List;

public class Scenario {
    static String border = "┌─────────────────────────────────────────────────────────────────────────────────────┐";

// HEALTHCARE
public static void scenario1() {
    Extra.centerText("A pandemic has hit the city. Hospitals are overwhelmed, and citizens are demanding better healthcare services.",border.length());
    System.out.println();

    List<String> scenarioChoice1 = new ArrayList<>();
    scenarioChoice1.add("Build new hospitals");
    scenarioChoice1.add("Funding to existing hospitals");
    scenarioChoice1.add("Ignore the situation");

    System.out.println(Extra.formatText("What should we do?",border.length()));
    displayChoices(scenarioChoice1);
}

// INFRASTRUCTURE
public static void scenario2() {
    Extra.centerText("A natural disaster (flood/earthquake) has hit the city, causing extensive damage to infrastructure. The citizens expect immediate government intervention.", border.length());
    System.out.println();

    List<String> scenarioChoice2 = new ArrayList<>();
    scenarioChoice2.add("Allocate full disaster relief funds");
    scenarioChoice2.add("Provide limited funds for essential repairs");
    scenarioChoice2.add("Do nothing");

    System.out.println(Extra.formatText("What should we do?",border.length()));
    displayChoices(scenarioChoice2);
}

// ENVIRONMENT
public static void scenario3() {
    Extra.centerText("Factories are polluting the air and water, affecting the environment. Citizens are demanding the government implement new environmental protection programs.", border.length());
    System.out.println();

    List<String> scenarioChoice3 = new ArrayList<>();
    scenarioChoice3.add("Launch a strict environmental protection program");
    scenarioChoice3.add("Impose some regulations");
    scenarioChoice3.add("Do nothing");

    System.out.println(Extra.formatText("What should we do?",border.length()));
    displayChoices(scenarioChoice3);
}

// MORALE
public static void scenario4() {
    Extra.centerText("Crime rates are rising across the city, leading to unrest and fear among citizens. There are calls for more policing and social programs.", border.length());
    System.out.println();

    List<String> scenarioChoice4 = new ArrayList<>();
    scenarioChoice4.add("Increase police funding and implement social programs");
    scenarioChoice4.add("Increase only police funding");
    scenarioChoice4.add("Do nothing");

    System.out.println(Extra.formatText("What should we do?",border.length()));
    displayChoices(scenarioChoice4);
}

// INNOVATION
public static void scenario5() {
    Extra.centerText("The economy is in decline. Unemployment is rising, and businesses are closing. Citizens demand an economic stimulus package.", border.length());
    System.out.println();

    List<String> scenarioChoice5 = new ArrayList<>();
    scenarioChoice5.add("Implement a large stimulus package");
    scenarioChoice5.add("Offer tax breaks to small businesses");
    scenarioChoice5.add("Do nothing");

    System.out.println(Extra.formatText("What should we do?",border.length()));
    displayChoices(scenarioChoice5);
}

// EDUCATION
public static void scenario6() {
    Extra.centerText("The city's education system is outdated, and teachers are demanding reforms to ensure better education for children.", border.length());
    System.out.println();

    List<String> scenarioChoice6 = new ArrayList<>();
    scenarioChoice6.add("Completely overhaul the education system");
    scenarioChoice6.add("Implement moderate reforms");
    scenarioChoice6.add("Ignore demands");

    System.out.println(Extra.formatText("What should we do?",border.length()));
    displayChoices(scenarioChoice6);
}

// Helper method to display choices in a formatted way
public static void displayChoices(List<String> choices) {
    for (int i = 0; i < choices.size(); i++) {
        System.out.println("\t\t" + (i + 1) + ". " + choices.get(i) + "\n");
    }
}

}
