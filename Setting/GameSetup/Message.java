package GameSetup;
public class Message {

  public static void tutorialMessage(int level){
      int critical;
      int fund;
      int morale = 0;
        switch (level) {
            case 1 -> {
                critical = 8;
                fund = 3;
                morale = 2;
            }
            case 2 -> {
                critical = 10;
                fund = 3;
                morale =1;
            }
            default -> {
                critical = 15;
                fund = 2;
            }
        }
      System.out.println("  ┌──────────────────────────────────────┐   ┌──────────────────────────────────────┐");
      System.out.println("         Funds and Morale are given");
      System.out.println("        based on the city Attribute's                 If one attribute reach");
      System.out.println("                    Status                             critical Level (<"+critical+"), ");
      System.out.println("              Attribute = \"HIGH\"                 Emergency Funds will be used." );
      System.out.println("            Funds + "+fund+", Morale + "+morale);
      System.out.println("            Attribute = \"MODERATE\"               If Funds is not sufficient,");
      System.out.println("                  Funds + "+(fund-1)+"                       The City tax will increase ");
      System.out.println("               Attribute = LOW                    Affecting the citizens Morale.");
      System.out.println("                  Funds + "+(fund-1));
      System.out.println("  └──────────────────────────────────────┘   └──────────────────────────────────────┘");
      System.out.println(Extra.formatText("┌──────────────────────────────────────┐"));
      System.out.println(Extra.formatText("3 \"Life\" are given."));
      System.out.println(Extra.formatText("A life will be deducted every time"));
      System.out.println(Extra.formatText("an attribute reach a critical level."));
      System.out.println(Extra.formatText("Losing all Life will result"));
      System.out.println(Extra.formatText("\"GAME OVER\""));
      System.out.println();
      System.out.println(Extra.formatText("Reaching a critical level on"));
      System.out.println(Extra.formatText("\"Fund\" will result to a Attribute"));
      System.out.println(Extra.formatText("reduction that will take (-5)"));
      System.out.println(Extra.formatText("points from each attribute."));
      System.out.println(Extra.formatText("└──────────────────────────────────────┘"));




    }

  public static void printAchievement(String title, int totalLength, int value) {
    int dashCount = totalLength - title.length() - String.valueOf(value).length() - 1;
    String dashes = "-".repeat(Math.max(0, dashCount));
    System.out.println(String.format("\t\t%s %s %d", title, dashes, value));
  }

  public static int achievementBonus(int level, City city, double attributeScore) {
    int totalBonus = 0;
    int totalLength = 50;

    if (city.getAttribute("health") >= 85 && city.getAttribute("education") >= 85 &&
        city.getAttribute("environment") >= 85 && city.getAttribute("innovation") >= 85) {
        printAchievement("CITY SAVIOR", totalLength, 100);
        totalBonus += 100;
    } else if (city.getAttribute("health") >= 50 && city.getAttribute("education") >= 50 &&
              city.getAttribute("environment") >= 50 && city.getAttribute("innovation") >= 50) {
        printAchievement("CITY BUILDER", totalLength, 50);
        totalBonus += 50;
    }

    if ("HIGH".equals(city.determineStatus(city.getAttribute("fund"))) &&
        "HIGH".equals(city.determineStatus(city.getAttribute("morale"))) &&
        "HIGH".equals(city.determineStatus(city.getAttribute("education"))) &&
        "HIGH".equals(city.determineStatus(city.getAttribute("innovation"))) &&
        "HIGH".equals(city.determineStatus(city.getAttribute("environment"))) &&
        "HIGH".equals(city.determineStatus(city.getAttribute("healthcare")))) {
        printAchievement("JACK OF ALL TRADES", totalLength, 200);
        totalBonus += 100;
    } else if ("MODERATE".equals(city.determineStatus(city.getAttribute("fund"))) &&
              "MODERATE".equals(city.determineStatus(city.getAttribute("morale"))) &&
              "MODERATE".equals(city.determineStatus(city.getAttribute("education"))) &&
              "MODERATE".equals(city.determineStatus(city.getAttribute("innovation"))) &&
              "MODERATE".equals(city.determineStatus(city.getAttribute("environment"))) &&
              "MODERATE".equals(city.determineStatus(city.getAttribute("healthcare")))) {
        printAchievement("LIVING IN MODERATION", totalLength, 150);
        totalBonus += 100;
    } else if ("!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("fund"))) &&
              "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("morale"))) &&
              "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("education"))) &&
              "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("innovation"))) &&
              "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("environment"))) &&
              "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("healthcare")))) {
        printAchievement("LIVING AT THE EDGE", totalLength, 50);
        totalBonus += 50;
    }

    if (level == 3 && attributeScore > 350) {
        printAchievement("HIGH RISK, HIGH REWARD", totalLength, 200);
        totalBonus += 200;
    }

    if (city.getAttribute("fund") >= 90) {
        printAchievement("FULLY FUNDED", totalLength, 50);
        totalBonus += 50;
    }

    if (city.getAttribute("environment") >= 90) {
        printAchievement("ENVIRONMENTAL HERO", totalLength, 50);
        totalBonus += 50;
    }

    if (city.getAttribute("education") >= 90) {
        printAchievement("EDUCATIONAL ADVOCATE", totalLength, 50);
        totalBonus += 50;
    }

    if (city.getAttribute("innovation") >= 90) {
        printAchievement("INNOVATION LEADER", totalLength, 50);
        totalBonus += 50;
    }

    if (city.getAttribute("healthcare") >= 90) {
        printAchievement("HEALTHCARE HERO", totalLength, 50);
        totalBonus += 50;
    }

    if (city.getAttribute("morale") >= 90) {
        printAchievement("CITIZEN'S CHAMPION", totalLength, 50);
        totalBonus += 50;
    }

    if (level == 3 && city.getAttribute("health") >= 50 && city.getAttribute("education") >= 50 &&
        city.getAttribute("environment") >= 50 && city.getAttribute("innovation") >= 50) {
        printAchievement("AGAINST ALL ODDS", totalLength, 80);
        totalBonus += 80;
    }

    if ("HIGH".equals(city.determineStatus(city.getAttribute("healthcare"))) &&
        "HIGH".equals(city.determineStatus(city.getAttribute("environment")))) {
        printAchievement("GREEN CITY", totalLength, 50);
        totalBonus += 50;
    }

    if (city.getAttribute("healthcare") >= 90 && city.getAttribute("fund") >=90) {
      printAchievement("HEALTH AND WEALTH", totalLength, 50);
      totalBonus += 50;
    }

    if (city.getAttribute("education") >= 75 && (city.getAttribute("education") >= city.getAttribute("environment")&&
        city.getAttribute("education") >= city.getAttribute("healthcare")&& 
        city.getAttribute("education") >= city.getAttribute("innovation"))) {
        printAchievement("EDUCATIONAL HUB", totalLength, 75);
        totalBonus += 75;
    }

    if (city.getAttribute("environment") >= 75 && (city.getAttribute("environment") >= city.getAttribute("education")&&
        city.getAttribute("environment") >= city.getAttribute("healthcare")&& 
        city.getAttribute("environment") >= city.getAttribute("innovation"))) {
        printAchievement("ENVIRONMENTALLY AWARE", totalLength, 75);
        totalBonus += 75;
    }

    if (city.getAttribute("innovation") >= 80 && (city.getAttribute("innovation") >= city.getAttribute("environment")&&
        city.getAttribute("innovation") >= city.getAttribute("healthcare")&& 
        city.getAttribute("innovation") >= city.getAttribute("education"))) {
        printAchievement("CITY OF THE FUTURE", totalLength, 75);
        totalBonus += 75;
    }

    if (city.getAttribute("healthcare") >= 80 && (city.getAttribute("healthcare") >= city.getAttribute("environment")&&
        city.getAttribute("healthcare") >= city.getAttribute("innovation")&& 
        city.getAttribute("healthcare") >= city.getAttribute("education"))) {
        printAchievement("HEALTHY CITY", totalLength, 75);
        totalBonus += 75;
    } 

    if ("HIGH".equals(city.determineStatus(city.getAttribute("healthcare"))) &&
        "HIGH".equals(city.determineStatus(city.getAttribute("innovation")))){
          printAchievement("HEALTHY INNOVATION", totalLength, 50);
          totalBonus += 50;
    }

    if ("HIGH".equals(city.determineStatus(city.getAttribute("education"))) &&
    "HIGH".equals(city.determineStatus(city.getAttribute("environment")))){
      printAchievement("EDUCATIONAL ECOSYSTEM", totalLength, 50);
      totalBonus += 50;
    }

    if ("HIGH".equals(city.determineStatus(city.getAttribute("education"))) &&
    "HIGH".equals(city.determineStatus(city.getAttribute("healthcare")))){
      printAchievement("HEALTH DEGREE", totalLength, 50);
      totalBonus += 50;
    }

    if ("HIGH".equals(city.determineStatus(city.getAttribute("innovation"))) &&
    "HIGH".equals(city.determineStatus(city.getAttribute("environment")))){
      printAchievement("ECO-INNOVATION", totalLength, 50);
      totalBonus += 50;
    }

    if ("HIGH".equals(city.determineStatus(city.getAttribute("education"))) &&
    "HIGH".equals(city.determineStatus(city.getAttribute("environment")))){
      printAchievement("EDUCATIONAL ECOSYSTEM", totalLength, 50);
      totalBonus += 50;
    }

    return totalBonus;
  }


  public static void deleteAccountMessage() {
    System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
    System.out.println("│                                                                          │");
    System.out.println("│  Thank you for playing City Simulator!                                   │");
    System.out.println("│                                                                          │");
    System.out.println("│  You've faced many tough decisions in guiding your city towards growth,  │");
    System.out.println("│  innovation, and sustainability. Each choice impacted the lives of       │");
    System.out.println("│  countless citizens, shaping the future of your city in unique ways.     │");
    System.out.println("│                                                                          │");
    System.out.println("│  Remember, leading a city is about balance and foresight. The challenges │");
    System.out.println("│  may change, but your decisions and values make the difference.          │");
    System.out.println("│                                                                          │");
    System.out.println("│  We hope you enjoyed the journey. Farewell, Mayor!                       │");
    System.out.println("│                                                                          │");
    System.out.println("└──────────────────────────────────────────────────────────────────────────┘"); 
    }

}
