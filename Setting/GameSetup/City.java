package GameSetup;
import java.util.HashMap;
import java.util.Map;

public class City {

    private Map<String, Integer> attributes;

    City(int healthcare, int education, int innovation, int environment, int fund, int morale) {
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
        if (value < 0) value = 0;
        else if (value > 100) value = 100;
        attributes.put(name, value);
    }


    ///Determine the status of attribute to display
    public String determineStatus(int percent) {
        if (percent <= 25) return "!!!-LOW-!!!";
        else if (percent <= 75)return "MODERATE";
        else return "HIGH";
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

        Extra.centerText("    YEARLY ADJUSTMENT IS ACTIVE! CERTAIN AMOUNT OF POINTS WILL BE DEDUCTED OR ADDED FROM EACH ATTRIBUTE");
        System.out.println();
        StringBuilder attributeLine = new StringBuilder();
        for (Map.Entry<String, Integer> entry : attributes.entrySet()) {
            attributeLine.append(entry.getKey().toUpperCase()).append(" (").append(entry.getValue()).append(") ");
        }
        
        System.out.println("  " + attributeLine.toString().trim()); 
        
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

}
