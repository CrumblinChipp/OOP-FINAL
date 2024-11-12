/// THINGS THAT NEED TO BE DONE
/// --CITY NAME INPUT FUNCTION
/// --ADDITIONAL SCENARIOS
/// --ADDITIONAL ACHIEVEMENT 
package GameSetup;
import Database.PlayerManager;
import Database.RankingManager;
import java.util.*;

public class Game {

    public static void GameRun(String user, int playerId){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Random random =new Random();
        int randomHealth = random.nextInt(40) + 25;
        int randomEdu = random.nextInt(40) + 25;   
        int randomInfra = random.nextInt(40) + 25;
        int randomEnvi = random.nextInt(40) + 25; 
        int randomFund = random.nextInt(45) + 30;  
        int randomMoral = random.nextInt(45) + 30;

        City city = new City(randomHealth, randomEdu, randomInfra, randomEnvi, randomFund, randomMoral);
        Scanner input = new Scanner(System.in);
        System.out.println("\t\t\t┌───────────────────────────────────┐");
        System. out.println("""
                                    \t\t\tDIFFICULTY 1
                                \t\t\t\t(Extra Bonus Points)\n
                                    \t\t\tDIFFICULTY 2
                            \t \t\t\t(Normal Difficulty)\n
                                    \t\t\tDIFFICULTY 3
                                \t\t\t\t(Bonus Points Reduced)\n""");

        int level;
        while (true) {
            System.out.print("\t\t\tPlease enter your choice (1, 2, or 3):");
            try {
                level = input.nextInt();
                input.nextLine(); 

                if (level >= 1 && level <= 3) {
                    break;
                } else {
                    System.out.println("\t\t\tInvalid input. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\t\t\tInvalid input. Please enter a number (1, 2, or 3).");
                input.nextLine(); 
            }
        }

        Message.tutorialMessage(level);
        Extra.clearScreen();
        
        List<Integer> scenarioNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int life = 3;
        int totalYear = 0;
        for (int year = 1 ;year <= 5 && life > 0; year++){
            List<Integer> scenarioList = new ArrayList<>(scenarioNumbers);
            int randomIndex = random.nextInt(scenarioList.size());
            final int randomScenario = scenarioList.get(randomIndex);

            city.printStatus( user, year, life);

            switch (randomScenario){
                case 1 -> Scenario.scenario1();
                case 2 -> Scenario.scenario2();
                case 3 -> Scenario.scenario3();
                case 4 -> Scenario.scenario4();
                case 5 -> Scenario.scenario5();
                case 6 -> Scenario.scenario6();
                case 7 -> Scenario.scenario7();
                case 8 -> Scenario.scenario8();
                case 9 -> Scenario.scenario9();
                case 10 -> Scenario.scenario10();
            }
            int choice;
            while (true) {
                System.out.print("\t\tPlease enter your choice (1, 2, or 3):");
                try {
                    choice = input.nextInt();
                    input.nextLine(); 
        
                    if (choice >= 1 && choice <= 3) {
                        break;
                    } else {
                        System.out.println("\t\tInvalid input. Please enter 1, 2, or 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\t\tInvalid input. Please enter a number (1, 2, or 3).");
                    input.nextLine(); 
                }
            }
            System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
            Decision.applyDecision(randomScenario, choice, city, level);
            city.pointBonusAllocation(level);
            city.decayEffect(level);
            life -= city.criticalSectorChecker(level);
            totalYear++;

            scenarioNumbers.remove(Integer.valueOf(randomScenario));
            Extra.clearScreen();
            input.nextLine();
        }

        double totalScore = city.printEndGame(level, user, city, totalYear, life);
        PlayerManager.recordGame(playerId, totalScore);
        RankingManager.updateRanking(playerId, totalScore);
    
        Extra.clearScreen();
    }
}
