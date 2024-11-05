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
      System.out.println("┌───────────────────────────────┐   ┌───────────────────────────────┐   ┌───────────────────────────────┐");
      System.out.println("   Funds and Morale are given                                                 3 \"Life\" are given.");
      System.out.println("  based on the city Attribute's          If one attribute reach        A life will be deducted every time");
      System.out.println("            Status                        critical Level (<"+ critical +"),         an attribute reach a critical level.");
      System.out.println("      Attribute = \"HIGH\"            Emergency Funds will be used.          Losing all Life will result" );
      System.out.println("      Funds + "+fund+", Morale + "+morale+"                                                         \"GAME OVER\"");
      System.out.println("     Attribute = \"MODERATE\"           If Funds is not sufficient,");
      System.out.println("           Funds + "+(fund-1)+"                   The City tax will increase         Reaching a critical level on");
      System.out.println("        Attribute = LOW               Affecting the citizens Morale     \"Fund\" will result to a Attribute");
      System.out.println("           Funds + "+(fund-1)+"                                                      reduction that will take (-5) ");
      System.out.println("└───────────────────────────────┘   └───────────────────────────────┘       points from each attribute.");
      System.out.println("                                                                        └───────────────────────────────┘");

    }

    public static int achievementBonus (int level, City city, double attributeScore){
      int totalBonus = 0;
      int totalLength = 50;
      if (city.getAttribute("health") >= 85 && city.getAttribute("education") >= 85 && 
      city.getAttribute("environment") >= 85 && city.getAttribute("innovation") >= 85){
        String totalDashes = new String(new char[totalLength - "CITY SAVIOR".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tCITY SAVIOR %s %d", totalDashes, 100));
        totalBonus += 100;

      } else if (city.getAttribute("health") >= 50 && city.getAttribute("education") >= 50 && 
      city.getAttribute("environment") >= 50 && city.getAttribute("innovation") >= 50){
        String totalDashes = new String(new char[totalLength - "CITY BUILDER".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tCITY BUILDER %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      if ("HIGH".equals(city.determineStatus(city.getAttribute("fund"))) && 
      "HIGH".equals(city.determineStatus(city.getAttribute("morale"))) && 
      "HIGH".equals(city.determineStatus(city.getAttribute("education"))) && 
      "HIGH".equals(city.determineStatus(city.getAttribute("innovation"))) &&
      "HIGH".equals(city.determineStatus(city.getAttribute("environment"))) && 
      "HIGH".equals(city.determineStatus(city.getAttribute("healthcare")))){
        String totalDashes = new String(new char[totalLength - "JACK OF ALL TRADES".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tJACK OF ALL TRADES %s %d", totalDashes, 200));
        totalBonus += 100;

      }else if ("MODERATE".equals(city.determineStatus(city.getAttribute("fund"))) && 
      "MODERATE".equals(city.determineStatus(city.getAttribute("morale"))) && 
      "MODERATE".equals(city.determineStatus(city.getAttribute("education"))) && 
      "MODERATE".equals(city.determineStatus(city.getAttribute("innovation"))) &&
      "MODERATE".equals(city.determineStatus(city.getAttribute("environment"))) && 
      "MODERATE".equals(city.determineStatus(city.getAttribute("healthcare")))){
        String totalDashes = new String(new char[totalLength - "LIVING IN MODERATION".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tLIVING IN MODERATION %s %d", totalDashes, 150));
        totalBonus += 100;

      }else if ("!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("fund"))) && 
      "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("morale"))) && 
      "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("education"))) && 
      "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("innovation"))) &&
      "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("environment"))) && 
      "!!!-LOW-!!!".equals(city.determineStatus(city.getAttribute("healthcare")))){
        String totalDashes = new String(new char[totalLength - "LIVING AT THE EDGE".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tLIVING AT THE EDGE %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      if (level == 3 && attributeScore > 350){
        String totalDashes = new String(new char[totalLength - "HIGH RISK, HIGH REWARD".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tHIGH RISK, HIGH REWARD %s %d", totalDashes, 200));
        totalBonus += 200;
      }

      if (city.getAttribute("fund") >= 90){
        String totalDashes = new String(new char[totalLength - "FULLY FUNDED".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tFULLY FUNDED %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      if (city.getAttribute("environment") >= 90){
        String totalDashes = new String(new char[totalLength - "ENVIRONMENTAL HERO".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tENVIRONMENTAL HERO %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      if (city.getAttribute("education") >= 90){
        String totalDashes = new String(new char[totalLength - "EDUCATIONAL ADVOCATE".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tEDUCATIONAL ADVOCATE %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      if (city.getAttribute("innovation") >= 90){
        String totalDashes = new String(new char[totalLength - "INNOVATION LEADER".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tINNOVATION LEADER %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      if (city.getAttribute("healthcare") >= 90){
        String totalDashes = new String(new char[totalLength - "HEALTHCARE HERO".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tHEALTHCARE HERO %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      if (city.getAttribute("morale") >= 90){
        String totalDashes = new String(new char[totalLength - "CITIZEN'S CHAMPION".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tCITIZEN'S CHAMPION %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      if (level == 3 && city.getAttribute("health") >= 50 && city.getAttribute("education") >= 50 && 
      city.getAttribute("environment") >= 50 && city.getAttribute("innovation") >= 50){
        String totalDashes = new String(new char[totalLength - "AGAINST ALL ODDS".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tAGAINST ALL ODDS %s %d", totalDashes, 80));
        totalBonus += 80;
      }

      if ("HIGH".equals(city.determineStatus(city.getAttribute("healthcare"))) && "HIGH".equals(city.determineStatus(city.getAttribute("environment")))){
        String totalDashes = new String(new char[totalLength - "GREEN CITY".length() - 1]).replace("\0", "-");
        System.out.println(String.format("\t\tGREEN CITY %s %d", totalDashes, 50));
        totalBonus += 50;
      }

      return totalBonus;
    }

}
