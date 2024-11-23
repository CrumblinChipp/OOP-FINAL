package GameSetup;

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

        String heart = "♥ ";
        StringBuilder hearts = new StringBuilder();
        for (int i = 0; i < life; i++) {
            hearts.append(heart);
        }
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println(  "  Life: "+hearts+"\t  \t\t! ! !"+user+"\'s City! ! !\n\n\tPlayer: " + user + "\t\t\tYear:" + year + "\t\t\tMorale: " + city.determineStatus(morale) + "\n");
        System.out.println(" ────────────────────────────────────────────────────────────────────────────────────");
        System.out.println(String.format("\t\t\t   %-20s %-23s ", "FUNDS", city.determineStatus(fund)));
        System.out.println(String.format("\t\t\t   %-44s \n", fundBar));

        System.out.println(String.format("\t%-20s %-16s%-20s %-23s ", "HEALTH", city.determineStatus(healthcare), "EDUCATION", city.determineStatus(education)));
        System.out.println(String.format("\t%-13s   %-44s \n", healthBar, educationBar));
    
        System.out.println(String.format("\t%-20s %-16s%-20s %-23s ", "ENVIRONMENT", city.determineStatus(environment), "INNOVATION", city.determineStatus(innovation)));
        System.out.println(String.format("\t%-12s   %-44s \n", enviBar, innovationBar));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
    
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

        switch (city.determineStatus(fund)) {
            case "HIGH" -> fundBonus = 100;
            case "MODERATE" -> fundBonus = 50;
            case "!!!-LOW-!!!" -> fundBonus = 25;
        }
        switch (city.determineStatus(morale)) {
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
