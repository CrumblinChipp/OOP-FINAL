package GameSetup;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class City {

    private Map<String, Integer> attributes;

    public City(int healthcare, int education, int innovation, int environment, int fund, int morale) {
        attributes = new HashMap<>();
        attributes.put("healthcare", healthcare);
        attributes.put("education", education);
        attributes.put("innovation", innovation);
        attributes.put("environment", environment);
        attributes.put("fund", fund);
        attributes.put("morale", morale);
    }

    public City() {
        this(0, 0, 0, 0, 0, 0);
    }

    public int getAttribute(String name) {
        return attributes.getOrDefault(name, 0);
    }

    public void setAttribute(String name, int value) {
        attributes.put(name, value);
    }

    ///bar creation
    public static String generateProgressBar(int length, int percent) {
        char incomplete = '░';
        char complete = '█';

        if (percent < 0) {
            percent = 0;
        } else if (percent > 100) {
            percent = 100; 
        }

        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(length).forEach(builder::append);
        int steps = (percent * length) / 100;
        for (int i = 0; i < steps; i++) {
            builder.replace(i, i + 1, String.valueOf(complete));
        }

        return builder.toString() + " " + percent + "%"; 
    }

    ///Determine the status of attribute to display
    public String determineStatus(int percent) {
        if (percent <= 25) {
            return "!!!-LOW-!!!";
        } else if (percent <= 75) {
            return "MODERATE";
        } else {
            return "HIGH";
        }
    }

    //Critical checking for attributes
    public String criticalCheck(int level, int healthcare, int environment, int education, int innovation) {
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
            return "HEALTH";
        } else if (critical == environment) {
            return "ENVIRONMENT";
        } else if (critical == education) {
            return "EDUCATION";
        } else if (critical == innovation) {
            return "INNOVATION";
        }
        return "NULL";
    } 

    ///fund and morale bonus point per year base on the attribute status and game level
    public void pointBonusAllocation ( int level){
        int[] statuses = {
            getAttribute("healthcare"),
            getAttribute("environment"),
            getAttribute("education"),
            getAttribute("innovation")
        };
        
        for (int status : statuses) {
            String currentStatus = determineStatus(status);
            switch (currentStatus) {
                case "HIGH" -> {
                    switch(level){
                        case 1 -> {
                            setAttribute("fund", getAttribute("fund") +3);
                            setAttribute("morale", getAttribute("morale") +2);
                        }
                        case 2 -> {
                            setAttribute("fund", getAttribute("fund") +3);
                            setAttribute("morale", getAttribute("morale") +1);
                        }
                        case 3 ->setAttribute("fund", getAttribute("fund") +2);

                    }
                }
                
                case "MODERATE" -> {
                    switch(level){
                        case 1 -> {
                            setAttribute("fund", getAttribute("fund") +2);
                            setAttribute("morale", getAttribute("morale") +1);
                        }
                        case 2 -> setAttribute("fund", getAttribute("fund") +2);
                        case 3 -> setAttribute("fund", getAttribute("fund") +1);
                    }
                }

                case "!!!-LOW-!!!" -> {
                    switch(level){
                        case 1 -> setAttribute("fund", getAttribute("fund") +2);
                        case 2 -> setAttribute("fund", getAttribute("fund") +1);
                    }
                }
            }
        }
    }

    ///attribute critical status checking and point application.
    public int criticalSectorChecker (int level){
        int limit = 0;
        switch (level) {
            case 1 -> limit = 8;
            case 2 -> limit = 10;
            case 3 -> limit = 15;
        }
        String critical = criticalCheck(level, getAttribute("healthcare"), getAttribute("environment"), getAttribute("education"), getAttribute("innovation"));
       
        if ("HEALTH".equals(critical) || "ENVIRONMENT".equals(critical) || "EDUCATION".equals(critical) || "INNOVATION".equals(critical)){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if (getAttribute("fund") < limit){
                if (getAttribute("morale") < limit){
                    System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("\t\t\t\t You have failed");
                    System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
                    return 3;
                }
                setAttribute("morale", getAttribute("morale") -10);
                System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
                System.out.println(  "  " + critical + " has reached a critical level. Tax have been raised to replenish the loss");
                System.out.println(  "                           (" + critical + " +20, MORAL -10)");
                System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
                System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
                switch (critical) {
                    case "HEALTH" -> setAttribute("healthcare", getAttribute("healthcare") +20);
                    case "ENVIRONMENT" -> setAttribute("environment", getAttribute("environment") +20);
                    case "EDUCATION" -> setAttribute("education", getAttribute("education") +20);
                    case "INNOVATION" -> setAttribute("innovation", getAttribute("innovation") +20);
                }
                return 1;
            }
            setAttribute("fund", getAttribute("fund") -10);
            System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            System.out.println(  "\t" + critical + " has reached a critical level. Emergency fund have been used");
            System.out.println(  "                             (" + critical + " +20, FUNDS -10)");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");

            switch (critical) {
                case "HEALTH" -> setAttribute("healthcare", getAttribute("healthcare") +20);
                case "ENVIRONMENT" -> setAttribute("environment", getAttribute("environment") +20);
                case "EDUCATION" -> setAttribute("education", getAttribute("education") +20);
                case "INNOVATION" -> setAttribute("innovation", getAttribute("innovation") +20);
                default -> {
                }
            } 
            return 1;

        }

        if (getAttribute("fund") <= limit) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            System.out.println(  "\tFUNDs has reached a critical level. Attribute points will be deducted");
            System.out.println(  "                             (FUNDS +20, ATTRIBUTE -5)");
            System.out.println(" !    !    !    !    !    !    !    !    !    !    !    !    !    !    !   !   !    !");
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
            setAttribute("fund", getAttribute("fund") + 20);
            setAttribute("education", getAttribute("education") -5);
            setAttribute("environment", getAttribute("environment") -5);
            setAttribute("innovation", getAttribute("innovation") -5);
            setAttribute("healthcare", getAttribute("healthcare") -5);
            return 1;
        }
        if (getAttribute("morale") <= limit) {
            System.out.println("""
                                MORALE has reached critical level. 
                                Each attribute will receive a deduction""");
            setAttribute("morale", getAttribute("morale") + 20);
            setAttribute("education", getAttribute("education") -5);
            setAttribute("environment", getAttribute("environment") -5);
            setAttribute("innovation", getAttribute("innovation") -5);
            setAttribute("healthcare", getAttribute("healthcare") -5);
            return 1;
        }
        return 0;
    }

    ///attribute decay per year. differ for each game level
    public void decayEffect (int level){
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
            setAttribute(attributeName, getAttribute(attributeName) + value); 
        }

        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("    YEARLY ADJUSTMENT IS ACTIVE! CERTAIN AMOUNT OF POINTS WILL BE DEDUCTED OR ADDED\n \t\t\t\t FROM EACH ATTRIBUTE");
        System.out.print("\t");
        StringBuilder attributeLine = new StringBuilder();
        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            attributeLine.append(entry.getKey().toUpperCase()).append(" (").append(entry.getValue()).append(") ");
        }
        
        System.out.println("  " + attributeLine.toString().trim()); 
        
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    ///to display status of city per year loop
    public void printStatus(int length, String user, int year, int life) {

        // Get the attribute values
        int healthcare = getAttribute("healthcare");
        int morale = getAttribute("morale");
        int environment = getAttribute("environment");
        int fund = getAttribute("fund");
        int education = getAttribute("education");
        int innovation = getAttribute("innovation");

        // Generate progress bars
        String healthBar = generateProgressBar(length, healthcare);
        String enviBar = generateProgressBar(length, environment);
        String fundBar = generateProgressBar(length, fund);
        String educationBar = generateProgressBar(length, education);
        String innovationBar = generateProgressBar(length, innovation);

        String heart = "O ";
        StringBuilder hearts = new StringBuilder();
        for (int i = 0; i < life; i++) {
            hearts.append(heart);
        }
        // Print the status
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println(  "  Life: "+hearts+"\t  \t\t!!!Rockwell City!!!\n\n\tPlayer: " + user + "\t\t\tYear:" + year + "\t\t\tMorale: " + determineStatus(morale) + "\n");
        System.out.println(" ────────────────────────────────────────────────────────────────────────────────────");
        System.out.println(String.format("\t\t\t   %-20s %-23s ", "FUNDS", determineStatus(fund)));
        System.out.println(String.format("\t\t\t   %-44s \n", fundBar));

        System.out.println(String.format("\t%-20s %-16s%-20s %-23s ", "HEALTH", determineStatus(healthcare), "EDUCATION", determineStatus(education)));
        System.out.println(String.format("\t%-13s   %-44s \n", healthBar, educationBar));
  
        System.out.println(String.format("\t%-20s %-16s%-20s %-23s ", "ENVIRONMENT", determineStatus(environment), "INNOVATION", determineStatus(innovation)));
        System.out.println(String.format("\t%-12s   %-44s \n", enviBar, innovationBar));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    ///to display the score 
    public double printEndGame(int level, String user, City city, int years, int life){
        int fundBonus = 0;
        double moraleMultiplier = 1;
        double levelMultiplier= 1;
        // Get the attribute values
        int healthcare = getAttribute("healthcare");
        int environment = getAttribute("environment");
        int education = getAttribute("education");
        int innovation = getAttribute("innovation");
        int fund = getAttribute("fund");
        int morale = getAttribute("morale");

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
        System.out.println("\t\t\t\t!!!Rockwell City!!!\n\n\tPlayer: " + user + "\t\tYears: "+years+"\t\t Life: "+hearts+"\n");
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
        double achievementBonus = (Message.achievementBonus(level, city, attributeScore));
        double totalScore = (attributeScore + achievementBonus)*levelMultiplier;
        System.out.println("");
        System.out.println("\t_________________________________________________________________");
        String totalDashes = new String(new char[totalLength - "TOTAL".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tTOTAL %s %.2f", totalDashes, totalScore));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");

        return totalScore;
    }
}
