package GameSetup;

public class Scenario {
    // HEALTHCARE
    public static void scenario1() {
        Extra.centerText("A pandemic has hit the city. Hospitals are overwhelmed, and citizens are demanding better healthcare services.");
        System.out.println();

        System.out.println(Extra.formatText("What should we do?"));

        System.out.println(Extra.formatText("[1] Build new hospitals          "));
        System.out.println(Extra.formatText("[2] Funding to existing hospitals"));
        System.out.println(Extra.formatText("[3] Ignore the situation         "));
    }

    // INFRASTRUCTURE
    public static void scenario2() {
        Extra.centerText("A natural disaster (flood/earthquake) has hit the city, causing extensive damage to infrastructure. The citizens expect immediate government intervention.");
        System.out.println();

        System.out.println(Extra.formatText("What should we do?"));

        System.out.println(Extra.formatText("[1] Allocate full disaster relief funds    "));
        System.out.println(Extra.formatText("[2] Provide limited funds for essential repairs"));
        System.out.println(Extra.formatText("[3] Do nothing                             "));
    }

    // ENVIRONMENT
    public static void scenario3() {
        Extra.centerText("Factories are polluting the air and water, affecting the environment. Citizens are demanding the government implement new environmental protection programs.");
        System.out.println();

        System.out.println(Extra.formatText("What should we do?"));

        System.out.println(Extra.formatText("[1] Launch a strict environmental protection program"));
        System.out.println(Extra.formatText("[2] Impose some regulations                          "));
        System.out.println(Extra.formatText("[3] Do nothing                                      "));
    }

    // MORALE
    public static void scenario4() {
        Extra.centerText("Crime rates are rising across the city, leading to unrest and fear among citizens. There are calls for more policing and social programs.");
        System.out.println();

        System.out.println(Extra.formatText("What should we do?"));

        System.out.println(Extra.formatText("[1] Increase police funding and implement social programs"));
        System.out.println(Extra.formatText("[2] Increase only police funding                         "));
        System.out.println(Extra.formatText("[3] Do nothing                                           "));
    }

    // INNOVATION
    public static void scenario5() {
        Extra.centerText("The economy is in decline. Unemployment is rising, and businesses are closing. Citizens demand an economic stimulus package.");
        System.out.println();

        System.out.println(Extra.formatText("What should we do?"));

        System.out.println(Extra.formatText("[1] Implement a large stimulus package     "));
        System.out.println(Extra.formatText("[2] Offer tax breaks to small businesses   "));
        System.out.println(Extra.formatText("[3] Do nothing                             "));
    }

    // EDUCATION
    public static void scenario6() {
        Extra.centerText("The city's education system is outdated, and teachers are demanding reforms to ensure better education for children.");
        System.out.println();

        System.out.println(Extra.formatText("What should we do?"));

        System.out.println(Extra.formatText("[1] Completely overhaul the education system"));
        System.out.println(Extra.formatText("[2] Implement moderate reforms               "));
        System.out.println(Extra.formatText("[3] Ignore demands                           "));
    }

}
