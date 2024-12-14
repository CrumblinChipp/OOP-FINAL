package GameSetup;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CityUI{

    private City city;

    public CityUI(City city) {
        this.city = city;
    }
    ///bar creation
    private static String generateProgressBar( int percent) {
        char incomplete = '░';
        char complete = '█';

        if (percent < 0) {
            percent = 0;
        } else if (percent > 100) {
            percent = 100; 
        }

        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(30).forEach(builder::append);
        int steps = (percent * 30) / 100;
        for (int i = 0; i < steps; i++) {
            builder.replace(i, i + 1, String.valueOf(complete));
        }

        return builder.toString() + " " + percent + "%"; 
    }

    ///to display status of city per year loop
    public void printStatus( String user, int year, int life) {

        int healthcare = city.getAttribute("healthcare");
        int morale = city.getAttribute("morale");
        int environment = city.getAttribute("environment");
        int fund = city.getAttribute("fund");
        int education = city.getAttribute("education");
        int innovation = city.getAttribute("innovation");

        String healthBar = generateProgressBar( healthcare);
        String enviBar = generateProgressBar( environment);
        String fundBar = generateProgressBar( fund);
        String educationBar = generateProgressBar( education);
        String innovationBar = generateProgressBar( innovation);

        String heart = "O ";
        StringBuilder hearts = new StringBuilder();
        for (int i = 0; i < life; i++) {
            hearts.append(heart);
        }
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println(  "  Life: "+hearts+"\t  \t\t! ! !"+user+"\'s City! ! !\n\n\tPlayer: " + user + "\t\t\tYear:" + year + "\t\t\tMorale: " + determineStatus(morale) + "\n");
        System.out.println(" ────────────────────────────────────────────────────────────────────────────────────");
        System.out.println(String.format("\t\t\t   %-20s %-23s ", "FUNDS", determineStatus(fund)));
        System.out.println(String.format("\t\t\t   %-44s \n", fundBar));

        System.out.println(String.format("\t%-20s %-16s%-20s %-23s ", "HEALTH", determineStatus(healthcare), "EDUCATION", determineStatus(education)));
        System.out.println(String.format("\t%-13s   %-44s \n", healthBar, educationBar));
    
        System.out.println(String.format("\t%-20s %-16s%-20s %-23s ", "ENVIRONMENT", determineStatus(environment), "INNOVATION", determineStatus(innovation)));
        System.out.println(String.format("\t%-12s   %-44s \n", enviBar, innovationBar));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
    
    //attribute decay per year. differ for each game level
    public void yearlyAdjustment (int level){
        Map<String, Integer> attributes = new HashMap<>();
        attributes.put("healthcare", 0);
        attributes.put("environment", 0);
        attributes.put("education", 0);
        attributes.put("innovation", 0);

        int maxChange = 5;
        int minChange = -5;

        for (String key : attributes.keySet()) {
            int randomValue = (int) (Math.random() * (maxChange - minChange + 1) + minChange);
            attributes.put(key, randomValue);
        }

        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            String attributeName = entry.getKey();
            int value = entry.getValue();
            city.setAttribute(attributeName, city.getAttribute(attributeName) + value);
        }

        Extra.centerText("    YEARLY ADJUSTMENT IS ACTIVE! CERTAIN AMOUNT OF POINTS WILL BE DEDUCTED OR ADDED FROM EACH ATTRIBUTE");
        System.out.println();
        StringBuilder attributeLine = new StringBuilder();
        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            attributeLine.append(entry.getKey().toUpperCase()).append(" (").append(entry.getValue()).append(") ");
        }
        
        System.out.println("  " + attributeLine.toString().trim());
        
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────┘");
    }

    //Determine the status of attribute to display
    public static String determineStatus(int percent) {
        if (percent <= 25) {return "!!!-LOW-!!!";}
        else if (percent <= 75){return "MODERATE";}
        else {return "HIGH";}
    }

    //Critical checking for attributes
    private String criticalCheck(int level, int healthcare, int environment, int education, int innovation) {
        int limit = 0;
        switch (level) {
            case 1 -> limit = 8;
            case 2 -> limit = 10;
            case 3 -> limit = 15;
        }
        int[] attribute = {healthcare, environment, education, innovation};
        int critical = 100;
        for (int percent : attribute) {
            if (percent < critical) {
                critical = percent;
            }
        }

        if (critical > limit) {
            return "NULL";
        } else if (critical == healthcare) {
            return "HEALTHCARE";
        } else if (critical == environment) {
            return "ENVIRONMENT";
        } else if (critical == education) {
            return "EDUCATION";
        } else if (critical == innovation) {
            return "INNOVATION";
        }
        return "NULL";
    } 

    //fund and morale bonus point per year base on the attribute status and game level
    public void pointBonusAllocation ( int level){
        int[] statuses = {
            city.getAttribute("healthcare"),
            city.getAttribute("environment"),
            city.getAttribute("education"),
            city.getAttribute("innovation")
        };
        
        for (int status : statuses) {
            String currentStatus = determineStatus(status);
        
            Map<String, int[]> fundAdjustments = Map.of(
                "HIGH", new int[]{3, 3, 2},
                "MODERATE", new int[]{2, 2, 1},
                "!!!-LOW-!!!", new int[]{2, 1, 0}
            );
        
            Map<String, int[]> moraleAdjustments = Map.of(
                "HIGH", new int[]{2, 1, 0},
                "MODERATE", new int[]{1, 0, 0},
                "!!!-LOW-!!!", new int[]{0, 0, 0}
            );
        
            applyAdjustment("fund", fundAdjustments, currentStatus, level);
            applyAdjustment("morale", moraleAdjustments, currentStatus, level);
        }
    }
    
    // Helper method for point allocation
    private void applyAdjustment(String attributeName, Map<String, int[]> adjustments, String status, int level) {
        if (adjustments.containsKey(status) && level > 0 && level <= 3) {
            int adjustment = adjustments.get(status)[level - 1];
            if (adjustment > 0) {
                city.setAttribute(attributeName, city.getAttribute(attributeName) + adjustment);
            }
        }
    }

    // attribute critical status checking and point application.
    public int criticalSectorChecker (int level){
        int limit = 0;
        switch (level) {
            case 1 -> limit = 8;
            case 2 -> limit = 10;
            case 3 -> limit = 15;
        }
        String critical = criticalCheck(level, city.getAttribute("healthcare"), city.getAttribute("environment"), city.getAttribute("education"), city.getAttribute("innovation"));
        if ("HEALTHCARE".equals(critical) || "ENVIRONMENT".equals(critical) || "EDUCATION".equals(critical) || "INNOVATION".equals(critical)){
            if (city.getAttribute("fund") < limit){
                if (city.getAttribute("morale") < limit){
                    Extra.centerText("You have failed");
                    System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
                    return 3;
                }
                System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
                System.out.println(  "  " + critical + " has reached a critical level. Tax have been raised to replenish the loss");
                System.out.println(  "                           (" + critical + " +20, MORAL -10)");
                System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
                System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
                adjustAttribute("morale", -10);
                adjustAttribute(critical.toLowerCase(), 20);
                return 1;
            }
            System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            System.out.println(  "\t" + critical + " has reached a critical level. Emergency fund have been used");
            System.out.println(  "                             (" + critical + " +20, FUNDS -10)");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
            adjustAttribute("fund", -10);
            adjustAttribute(critical.toLowerCase(), 20);
            return 1;
        }
        if (city.getAttribute("fund") <= limit) {
            System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            Extra.formatText("FUND has reached a critical level. Attribute points will be deducted");
            Extra.formatText("(FUND +20, ATTRIBUTE -5)");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
            adjustAttribute("fund", 20);
            deductAttributes(new String[]{"education", "environment", "innovation", "healthcare"}, 5);
            return 1;
        }
        if (city.getAttribute("morale") <= limit) {
            Extra.centerText(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            Extra.formatText("MORALE has reached critical level. Each attribute will receive a deduction");
            Extra.formatText("(MORALE +20, ATTRIBUTE -5)");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
            adjustAttribute("morale", 20);
            deductAttributes(new String[]{"education", "environment", "innovation", "healthcare"}, 5);
            return 1;
        }
        return 0;
    }

    //helper method for critical checking
    private void adjustAttribute(String attribute, int value) {
        city.setAttribute(attribute, city.getAttribute(attribute) + value);
    }
    private void deductAttributes(String[] attributes, int deduction) {
        for (String attribute : attributes) {
            adjustAttribute(attribute, -deduction);
        }
    }
    
    //print end game result and return a total score to be recorded
    public double printEndGame(int level, String user, City city, int years, int life){
        int fundBonus = 0;
        double moraleMultiplier = 1;
        double levelMultiplier= 1;
        // Get the attribute values
        int healthcare = city.getAttribute("healthcare");
        int environment = city.getAttribute("environment");
        int education = city.getAttribute("education");
        int innovation = city.getAttribute("innovation");
        int fund = city.getAttribute("fund");
        int morale = city.getAttribute("morale");

        switch (determineStatus(fund)) {
            case "HIGH" -> fundBonus = 100;
            case "MODERATE" -> fundBonus = 50;
            case "!!!-LOW-!!!" -> fundBonus = 25;
        }
        switch (determineStatus(morale)) {
            case "HIGH" -> moraleMultiplier = 1.2;
            case "MODERATE" -> moraleMultiplier = 1.0;
            case "!!!-LOW-!!!" -> moraleMultiplier = 0.8;
        }
        switch (level) {
            case 1 -> levelMultiplier = 0.8;
            case 2 -> levelMultiplier = 1;
            case 3-> levelMultiplier = 1.2;
        }
        double attributeScore = (healthcare + environment + education + innovation + fundBonus)*moraleMultiplier;

        // Print the status
        int totalLength = 50;
        String heart = "O ";
        StringBuilder hearts = new StringBuilder();
        for (int i = 0; i < life; i++) {
            hearts.append(heart);
        }
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("\t\t\t\t!!!"+user+"\'s City!!!\n\n\tPlayer: " + user + "\t\tYears: "+years+"\t\t Life: "+hearts+"\n");
        System.out.println(" ────────────────────────────────────────────────────────────────────────────────────");
        System.err.println("\t\t\t\t      SCORE");

        String healthDashes = new String(new char[totalLength - "HEALTH".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tHEALTH %s %d", healthDashes, healthcare));

        String environmentDashes = new String(new char[totalLength - "ENVIRONMENT".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tENVIRONMENT %s %d", environmentDashes, environment));

        String educationDashes = new String(new char[totalLength - "EDUCATION".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tEDUCATION %s %d", educationDashes, education));

        String innovationDashes = new String(new char[totalLength - "INNOVATION".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tINNOVATION %s %d", innovationDashes, innovation));
        String moraleDashes = new String(new char[totalLength - "MORALE MULTIPLIER".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tMORALE MULTIPLIER %s x%.2f", moraleDashes, moraleMultiplier));
        double achievementBonus = (Message.achievementBonus(level, city, attributeScore));
        double totalScore = (attributeScore + achievementBonus)*levelMultiplier;
        String levelDashes = new String(new char[totalLength - "LEVEL MULTIPLIER".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tLEVEL MULTIPLIER %s x%.2f", levelDashes, levelMultiplier));
        System.out.println();
        System.out.println("\t_________________________________________________________________");
        String totalDashes = new String(new char[totalLength - "TOTAL".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tTOTAL %s %.2f", totalDashes, totalScore));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");

        return totalScore;
    }
    
}
