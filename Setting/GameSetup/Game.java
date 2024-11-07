/// THINGS THAT NEED TO BE DONE
/// --CITY NAME INPUT FUNCTION
/// --ADDITIONAL SCENARIOS
/// --ADDITIONAL ACHIEVEMENT 
package GameSetup;
import Database.PlayerManager;
import Database.RankingManager;
import java.util.*;

public class Game {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void GameRun(String user, int playerId){

        Random random =new Random();
        int randomHealth = random.nextInt(40) + 25;
        int randomEdu = random.nextInt(40) + 25;   
        int randomInfra = random.nextInt(40) + 25;
        int randomEnvi = random.nextInt(40) + 25; 
        int randomFund = random.nextInt(45) + 30;  
        int randomMoral = random.nextInt(45) + 30;

        City city = new City(randomHealth, randomEdu, randomInfra, randomEnvi, randomFund, randomMoral);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t┌───────────────────────────────────┐");
        System. out.println("""
                                 \t\t\t\tDIFFICULTY 1
                             \t\t\t\t(Extra Bonus Points)\n
                                 \t\t\t\tDIFFICULTY 2
                            \t \t\t\t(Normal Difficulty)\n
                                 \t\t\t\tDIFFICULTY 3
                             \t\t\t\t(Bonus Points Reduced)\n""");

        int level;
        while (true) {
            System.out.print("\t\t\tPlease enter your choice (1, 2, or 3):");
            try {
                level = scanner.nextInt();
                scanner.nextLine(); 
    
                if (level >= 1 && level <= 3) {
                    break;
                } else {
                    System.out.println("\t\t\tInvalid input. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\t\tInvalid input. Please enter a number (1, 2, or 3).");
                scanner.nextLine(); 
            }
        }

        Message.tutorialMessage(level);
        System.out.println("\t\t\t┌───────────────────────────────┐");
        System.out.println(  "\t\t\t   PRESS ENTER TO CONTINUE..");
        System.out.println("\t\t\t└───────────────────────────────┘");
        scanner.nextLine();
        clearScreen();
        List<Integer> scenarioNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int life = 3;
        int totalYear = 0;
        for (int year = 1 ;year <= 5 && life > 0; year++){
            List<Integer> scenarioList = new ArrayList<>(scenarioNumbers);
            int randomIndex = random.nextInt(scenarioList.size());
            final int randomScenario = scenarioList.get(randomIndex);

            city.printStatus(30, user, year, life);

            switch (randomScenario){
                case 1 -> Scenario.scenario1();
                case 2 -> Scenario.scenario2();
                case 3 -> Scenario.scenario3();
                case 4 -> Scenario.scenario4();
                case 5 -> Scenario.scenario5();
                case 6 -> Scenario.scenario6();
            }
            int choice;
            while (true) {
                System.out.print("\t\tPlease enter your choice (1, 2, or 3):");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); 
        
                    if (choice >= 1 && choice <= 3) {
                        break;
                    } else {
                        System.out.println("\t\tInvalid input. Please enter 1, 2, or 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\t\tInvalid input. Please enter a number (1, 2, or 3).");
                    scanner.nextLine(); 
                }
            }
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
            switch(randomScenario){
                case 1 -> {
                    switch (choice) {
                        case 1 ->  {
                            DecisionScenario1 DS1 = new DecisionScenario1(null);
                            DS1.applyEffectHigh(city, level);
                        }
                        case 2 -> {
                            DecisionScenario1 DS1 = new DecisionScenario1(null);
                            DS1.applyEffectModerate(city, level);
                        }
                        case 3 -> {
                            DecisionScenario1 DS1 = new DecisionScenario1(null);
                            DS1.applyEffectNone(city, level);
                        }

                    }
                }

                case 2 -> {
                    switch (choice) {
                        case 1 ->  {
                            DecisionScenario2 DS2 = new DecisionScenario2(null);
                            DS2.applyEffectHigh(city, level);
                        }
                        case 2 -> {
                            DecisionScenario2 DS2 = new DecisionScenario2(null);
                            DS2.applyEffectModerate(city, level);
                        }
                        case 3 -> {
                            DecisionScenario2 DS2 = new DecisionScenario2(null);
                            DS2.applyEffectNone(city, level);
                        }
                    }
                }

                case 3 -> {
                    switch (choice) {
                        case 1 ->  {
                            DecisionScenario3 DS3 = new DecisionScenario3(null);
                            DS3.applyEffectHigh(city, level);
                        }
                        case 2 -> {
                            DecisionScenario3 DS3 = new DecisionScenario3(null);
                            DS3.applyEffectModerate(city, level);
                        }
                        case 3 -> {
                            DecisionScenario3 DS3 = new DecisionScenario3(null);
                            DS3.applyEffectNone(city, level);
                        }
                    }
                }
                case 4 -> {
                    switch (choice) {
                        case 1 ->  {
                            DecisionScenario4 DS4 = new DecisionScenario4(null);
                            DS4.applyEffectHigh(city, level);
                        }
                        case 2 -> {
                            DecisionScenario4 DS4 = new DecisionScenario4(null);
                            DS4.applyEffectModerate(city, level);
                        }
                        case 3 -> {
                            DecisionScenario4 DS4 = new DecisionScenario4(null);
                            DS4.applyEffectNone(city, level);
                        }
                    }
                }

                case 5 -> {
                    switch (choice) {
                        case 1 ->  {
                            DecisionScenario5 DS5 = new DecisionScenario5(null);
                            DS5.applyEffectHigh(city, level);
                        }
                        case 2 -> {
                            DecisionScenario5 DS5 = new DecisionScenario5(null);
                            DS5.applyEffectModerate(city, level);
                        }
                        case 3 -> {
                            DecisionScenario5 DS5 = new DecisionScenario5(null);
                            DS5.applyEffectNone(city, level);
                        }
                    }
                }

                case 6 -> {
                    switch (choice) {
                        case 1 ->  {
                            DecisionScenario6 DS6 = new DecisionScenario6(null);
                            DS6.applyEffectHigh(city, level);
                        }
                        case 2 -> {
                            DecisionScenario6 DS6 = new DecisionScenario6(null);
                            DS6.applyEffectModerate(city, level);
                        }
                        case 3 -> {
                            DecisionScenario6 DS6 = new DecisionScenario6(null);
                            DS6.applyEffectNone(city, level);
                        }
                    }
                }

            }

            city.pointBonusAllocation(level);
            city.decayEffect(level);
            life -= city.criticalSectorChecker(level);
            totalYear++;

            scenarioNumbers.remove(Integer.valueOf(randomScenario));
            System.out.println("\t\t\t┌───────────────────────────────┐");
            System.out.println(  "\t\t\t    PRESS ENTER TO CONTINUE..");
            System.out.println("\t\t\t└───────────────────────────────┘");
            scanner.nextLine();
            clearScreen();
        }

        double totalScore = city.printEndGame(level, user, city, totalYear, life);
        PlayerManager.recordGame(playerId, totalScore);
        RankingManager.updateRanking(playerId, totalScore);
        System.out.println("\t\t\t┌───────────────────────────────┐");
        System.out.println(  "\t\t\t    PRESS ENTER TO CONTINUE..");
        System.out.println("\t\t\t└───────────────────────────────┘");
        scanner.nextLine();
        clearScreen();
    }
}
