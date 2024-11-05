package GameSetup;

public abstract class Decision {
    String description;

    public Decision(String description) {
        this.description = description;
    }

    // Abstract method - must be implemented by subclasses
    public abstract void applyEffectHigh(City city, int level);
    public abstract void applyEffectModerate(City city, int level);
    public abstract void applyEffectNone(City city, int level);
    String border = "┌─────────────────────────────────────────────────────────────────────────────────────┐";

    ///method for applying points on each decision.Varies to each level and decision. made to have general point system.
    public static void pointAllocation (int level, int decision,City city, String primaryAttribute, String secondaryAttribute){
        int fundValue = city.getAttribute("fund");
        int primaryAttributeValue = city.getAttribute(primaryAttribute);
        int secondaryAttributeValue = city.getAttribute(secondaryAttribute);
        int maxAttribute = 0;
        int minAttribute = 0;
        int maxSecondaryAttribute = 0;
        int minSecondaryAttribute = 0;
        int maxFund = 0;
        int minFund = 0;
        switch (level){
            case 1 -> {
                switch(decision){
                    case 1 -> ///high end
                            {
                        maxFund = -15;
                        minFund = -12;
                        maxAttribute = 20;
                        minAttribute = 15;
                        maxSecondaryAttribute = 5;
                        minSecondaryAttribute = 1;
                    }
                            
                    case 2 -> ///bare minimum
                            {
                        maxFund = -13;
                        minFund = -10;
                        maxAttribute = 15;
                        minAttribute = 10;
                    }
                            
                    case 3 -> //Do nothing 
                            {
                        maxAttribute = -15;
                        minAttribute = -10;
                        maxSecondaryAttribute = -3;
                        minSecondaryAttribute = -1;
                    }

                }
            }

            case 2 -> {
                switch(decision){
                    case 1 -> ///high end
                            {
                        maxFund = -16;
                        minFund = -12;
                        maxAttribute = 18;
                        minAttribute = 14;
                        maxSecondaryAttribute = 4;
                        minSecondaryAttribute = 1;
                    }
                            
                    case 2 -> ///bare minimum
                            {
                        maxFund = -14;
                        minFund = -10;
                        maxAttribute = 13;
                        minAttribute = 8;
                    }
                            
                    case 3 -> //Do nothing 
                            {
                        maxAttribute = -15;
                        minAttribute = -10;
                        maxSecondaryAttribute = -3;
                        minSecondaryAttribute = -2;
                    }

                }
            }

            case 3 -> {
                switch(decision){
                    case 1 -> ///high end
                            {
                        maxFund = -17;
                        minFund = -14;
                        maxAttribute = 16;
                        minAttribute = 10;
                        maxSecondaryAttribute = 3;
                        minSecondaryAttribute = 1;
                    }
                            
                    case 2 -> ///bare minimum
                            {
                        maxFund = -15;
                        minFund = -12;
                        maxAttribute = 15;
                        minAttribute = 10;
                    }
                            
                    case 3 -> //Do nothing 
                            {
                        maxAttribute = -18;
                        minAttribute = -13;
                        maxSecondaryAttribute = -4;
                        minSecondaryAttribute = -2;
                    }

                }
            }


        }

        int primaryBaseChange;
        int secondaryBaseChange;
        int fundChange;
        primaryBaseChange = (int)(Math.random() * (maxAttribute - minAttribute + 1) + minAttribute);
        secondaryBaseChange = (int)(Math.random() * (maxSecondaryAttribute - minSecondaryAttribute + 1) + minSecondaryAttribute);
        fundChange = (int)(Math.random() * (maxFund - minFund + 1) + minFund);

        primaryAttributeValue += primaryBaseChange;
        secondaryAttributeValue += secondaryBaseChange;
        fundValue += fundChange;

        city.setAttribute(primaryAttribute, primaryAttributeValue);
        city.setAttribute(secondaryAttribute, secondaryAttributeValue);
        city.setAttribute("fund", fundValue);
    }

    // Getter for the decision description
    public String getDescription() {
        return description;
    }

    protected String centerText(String text) {
        int padding = (border.length() - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }
        centeredText.append(text);
        return centeredText.toString();
    }

}
// HOSPITAL CRISIS
class DecisionScenario1 extends Decision {

    public DecisionScenario1(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "healthcare", "morale");
        System.out.println(border);
        System.out.println(centerText("New hospitals are under construction to handle the influx of patients!"));
        System.out.println(centerText("(+)HEALTH    (-)FUND    (+)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "healthcare", "morale");
        System.out.println(border);
        System.out.println(centerText("Existing hospitals receive additional funding to improve services."));
        System.out.println(centerText("(+)HEALTH    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 2, city, "healthcare", "morale");
        System.out.println(border);
        System.out.println(centerText("The situation worsens as healthcare demands continue to rise."));
        System.out.println(centerText("(-)HEALTH    (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}

// DISASTER CRISIS
class DecisionScenario2 extends Decision {

    public DecisionScenario2(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "innovation", "morale");
        System.out.println(border);
        System.out.println(centerText("Full disaster relief fund allocated. Citizens are relieved!"));
        System.out.println(centerText("(+)INNOVATION    (-)FUND    (+)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "innovation", "morale");
        System.out.println(border);
        System.out.println(centerText("Limited fund provided. Essential repairs begin, but citizens remain concerned."));
        System.out.println(centerText("(+)INNOVATION    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "innovation", "morale");
        System.out.println(border);
        System.out.println(centerText("No action taken. Citizens are frustrated, and infrastructure worsens."));
        System.out.println(centerText("(-)INNOVATION    (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}

// ENVIRONMENTAL CRISIS
class DecisionScenario3 extends Decision {

    public DecisionScenario3(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "environment", "healthcare");
        System.out.println(border);
        System.out.println(centerText("Environmental standards are now stricter, and pollution levels have decreased"));
        System.out.println(centerText("(+)ENVIRONMENT    (+)HEALTH   (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "environment", "morale");
        System.out.println(border);
        System.out.println(centerText("Some new regulations are in place, aiming to reduce pollution"));
        System.out.println(centerText("(+)ENVIRONMENT   (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "environment", "morale");
        System.out.println(border);
        System.out.println(centerText("Pollution continues, and citizens grow more concerned about the environment"));
        System.out.println(centerText("(-)ENVIRONMENT     (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}

// POLICE CRISIS
class DecisionScenario4 extends Decision {

    public DecisionScenario4(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "morale", "healthcare");
        System.out.println(border);
        System.out.println(centerText("Police funding and community programs are ramped up. Citizens feel relief and hope."));
        System.out.println(centerText("(+)MORALE    (+)HEALTH   (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "morale", "morale");
        System.out.println(border);
        System.out.println(centerText("Police funding increases, boosting security, but some citizens feel overlooked."));
        System.out.println(centerText("(+)MORALE   (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "morale", "morale");
        System.out.println(border);
        System.out.println(centerText("Tensions rise as no action is taken. The city struggles with growing unease."));
        System.out.println(centerText("(-)MORALE    (-)HEALTH"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}

// ECONOMIC STIMULUS CRISIS
class DecisionScenario5 extends Decision {

    public DecisionScenario5(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "innovation", "morale");
        System.out.println(border);
        System.out.println(centerText("The stimulus package injects capital into the economy,"));
        System.out.println(centerText("hoping to create jobs and revive businesses."));
        System.out.println(centerText("(+)INNOVATION    (+)MORALE   (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "innovation", "morale");
        System.out.println(border);
        System.out.println(centerText("Small businesses receive tax breaks, easing their"));
        System.out.println(centerText("financial burdens and encouraging growth."));
        System.out.println(centerText("(+)INNOVATION   (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "innovation", "morale");
        System.out.println(border);
        System.out.println(centerText("The economy continues to struggle as citizens grow"));
        System.out.println(centerText("frustrated with the lack of intervention."));
        System.out.println(centerText("(-)INNOVATION    (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}

// EDUCATION REFORM CRISIS
class DecisionScenario6 extends Decision {

    public DecisionScenario6(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "education", "morale");
        System.out.println(border);
        System.out.println(centerText("The city embarks on a major educational transformation,"));
        System.out.println(centerText("hoping to reshape the future for its youth."));
        System.out.println(centerText("(+)EDUCATION    (+)MORALE   (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "education", "morale");
        System.out.println(border);
        System.out.println(centerText("The city takes steps to improve education gradually,"));
        System.out.println(centerText("balancing progress with stability."));
        System.out.println(centerText("(+)EDUCATION   (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "education", "morale");
        System.out.println(border);
        System.out.println(centerText("The city overlooks the teachers' demands,"));
        System.out.println(centerText("leaving the system as it is for now."));
        System.out.println(centerText("(-)EDUCATION    (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}
