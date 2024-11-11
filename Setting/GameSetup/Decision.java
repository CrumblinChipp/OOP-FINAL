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
                    case 1 ->{
                        maxFund = -15;
                        minFund = -12;
                        maxAttribute = 20;
                        minAttribute = 15;
                        maxSecondaryAttribute = 5;
                        minSecondaryAttribute = 1;
                    }
                            
                    case 2 -> {
                        maxFund = -13;
                        minFund = -10;
                        maxAttribute = 15;
                        minAttribute = 10;
                    }
                            
                    case 3 -> {
                        maxAttribute = -15;
                        minAttribute = -10;
                        maxSecondaryAttribute = -3;
                        minSecondaryAttribute = -1;
                    }
                }
            }

            case 2 -> {
                switch(decision){
                    case 1 -> {
                        maxFund = -16;
                        minFund = -12;
                        maxAttribute = 18;
                        minAttribute = 14;
                        maxSecondaryAttribute = 4;
                        minSecondaryAttribute = 1;
                    }
                            
                    case 2 ->  {
                        maxFund = -14;
                        minFund = -10;
                        maxAttribute = 13;
                        minAttribute = 8;
                    }
                            
                    case 3 ->  {
                        maxAttribute = -15;
                        minAttribute = -10;
                        maxSecondaryAttribute = -3;
                        minSecondaryAttribute = -2;
                    }
                }
            }

            case 3 -> {
                switch(decision){
                    case 1 ->  {
                        maxFund = -17;
                        minFund = -14;
                        maxAttribute = 16;
                        minAttribute = 10;
                        maxSecondaryAttribute = 3;
                        minSecondaryAttribute = 1;
                    }
                            
                    case 2 -> {
                        maxFund = -15;
                        minFund = -12;
                        maxAttribute = 15;
                        minAttribute = 10;
                    }
                            
                    case 3 ->  {
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

    private static Decision getDecisionForScenario(int scenarioNumber) {
        return switch (scenarioNumber) {
            case 1 -> new DecisionScenario1(null);
            case 2 -> new DecisionScenario2(null);
            case 3 -> new DecisionScenario3(null);
            case 4 -> new DecisionScenario4(null);
            case 5 -> new DecisionScenario5(null);
            case 6 -> new DecisionScenario6(null);
            case 7 -> new DecisionScenario7(null);
            case 8 -> new DecisionScenario8(null);
            case 9 -> new DecisionScenario9(null);
            case 10 -> new DecisionScenario10(null);
            default -> throw new IllegalArgumentException("Invalid scenario number");
        };
    }

    public static void applyDecision(int randomScenario, int choice, City city, int level) {
        Decision decision = getDecisionForScenario(randomScenario);

        switch (choice) {
            case 1 -> decision.applyEffectHigh(city, level);
            case 2 -> decision.applyEffectModerate(city, level);
            case 3 -> decision.applyEffectNone(city, level);
            default -> throw new IllegalArgumentException("Invalid choice");
        }
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
        Extra.centerText("New hospitals are under construction to handle the influx of patients! ");
        System.out.println(Extra.formatText("(+)HEALTH    (-)FUND    (+)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "healthcare", "morale");
        Extra.centerText("Existing hospitals receive additional funding to improve services.");
        System.out.println(Extra.formatText("(+)HEALTH    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "healthcare", "morale");
        Extra.centerText("The situation worsens as healthcare demands continue to rise.");
        System.out.println(Extra.formatText("(-)HEALTH    (-)MORALE"));
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
        Extra.centerText("Full disaster relief fund allocated. Citizens are relieved!");
        System.out.println(Extra.formatText("(+)INNOVATION    (-)FUND    (+)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "innovation", "morale");
        Extra.centerText("Limited fund provided. Essential repairs begin, but citizens remain concerned.");
        System.out.println(Extra.formatText("(+)INNOVATION    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "innovation", "morale");
        Extra.centerText("No action taken. Citizens are frustrated, and infrastructure worsens.");
        System.out.println(Extra.formatText("(-)INNOVATION    (-)MORALE"));
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
        Extra.centerText("Environmental standards are now stricter, and pollution levels have decreased.");
        System.out.println(Extra.formatText("(+)ENVIRONMENT    (+)HEALTH    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "environment", "morale");
        Extra.centerText("Some new regulations are in place, aiming to reduce pollution.");
        System.out.println(Extra.formatText("(+)ENVIRONMENT    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "environment", "morale");
        Extra.centerText("Pollution continues, and citizens grow more concerned about the environment.");
        System.out.println(Extra.formatText("(-)ENVIRONMENT    (-)MORALE"));
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
        Extra.centerText("Police funding and community programs are ramped up. Citizens feel relief and hope.");
        System.out.println(Extra.formatText("(+)MORALE    (+)HEALTH    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "morale", "morale");
        Extra.centerText("Police funding increases, boosting security, but some citizens feel overlooked.");
        System.out.println(Extra.formatText("(+)MORALE    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "morale", "morale");
        Extra.centerText("Tensions rise as no action is taken. The city struggles with growing unease.");
        System.out.println(Extra.formatText("(-)MORALE    (-)HEALTH"));
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
        Extra.centerText("The stimulus package injects capital into the economy, hoping to create jobs and revive businesses.");
        System.out.println(Extra.formatText("(+)INNOVATION    (+)MORALE    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "innovation", "morale");
        Extra.centerText("Small businesses receive tax breaks, easing their financial burdens and encouraging growth.");
        System.out.println(Extra.formatText("(+)INNOVATION    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "innovation", "morale");
        Extra.centerText("The economy continues to struggle as citizens grow frustrated with the lack of intervention.");
        System.out.println(Extra.formatText("(-)INNOVATION    (-)MORALE"));
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
        Extra.centerText("The city embarks on a major educational transformation, hoping to reshape the future for its youth.");
        System.out.println(Extra.formatText("(+)EDUCATION    (+)MORALE    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "education", "morale");
        Extra.centerText("The city takes steps to improve education gradually, balancing progress with stability.");
        System.out.println(Extra.formatText("(+)EDUCATION    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "education", "morale");
        Extra.centerText("The city overlooks the teachers' demands, leaving the system as it is for now.");
        System.out.println(Extra.formatText("(-)EDUCATION    (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
    
}

// EDUCATION TECHNOLOGY
class DecisionScenario7 extends Decision {
    public DecisionScenario7(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "education", "innovation");
        Extra.centerText("The investment in digital learning resources boosts access to education and encourages student engagement.");
        System.out.println(Extra.formatText("(+)EDUCATION    (+)INNOVATION    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "education", "morale");
        Extra.centerText("Scholarships help more students access higher education, boosting morale and skills.");
        System.out.println(Extra.formatText("(+)EDUCATION    (+)MORALE    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "education", "morale");
        Extra.centerText("The lack of support for educational resources leads to increased dropout rates and public discontent.");
        System.out.println(Extra.formatText("(-)EDUCATION    (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}

// ENVIRONMENT POLLUTION
class DecisionScenario8 extends Decision {
    public DecisionScenario8(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "environment", "healthcare");
        Extra.centerText("Stricter regulations reduce pollution, improving public health and environmental quality.");
        System.out.println(Extra.formatText("(+)ENVIRONMENT    (+)HEALTHCARE    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "environment", "morale");
        Extra.centerText("The cleanup and reforestation program beautifies the city and raises morale.");
        System.out.println(Extra.formatText("(+)ENVIRONMENT    (+)MORALE    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "environment", "morale");
        Extra.centerText("Pollution worsens, negatively impacting both the environment and public morale.");
        System.out.println(Extra.formatText("(-)ENVIRONMENT    (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}

class DecisionScenario9 extends Decision {
    public DecisionScenario9(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "healthcare", "morale");
        Extra.centerText("Expanding healthcare infrastructure provides better access to medical care, boosting public morale.");
        System.out.println(Extra.formatText("(+)HEALTHCARE    (+)MORALE    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "healthcare", "fund");
        Extra.centerText("Increasing subsidies for essential medicines improves access to healthcare with moderate costs.");
        System.out.println(Extra.formatText("(+)HEALTHCARE    (-)FUND"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "healthcare", "morale");
        Extra.centerText("Healthcare remains inadequate, leading to public dissatisfaction and a decline in morale.");
        System.out.println(Extra.formatText("(-)HEALTHCARE    (-)MORALE"));
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────┘");
    }
}

// INNOVATION FESTIVAL
class DecisionScenario10 extends Decision {
    public DecisionScenario10(String description) {
        super(description);
    }

    @Override
    public void applyEffectHigh(City city, int level) {
        pointAllocation(level, 1, city, "innovation", "morale");
        Extra.centerText("The tech festival attracts talent, boosting morale and innovation.");
        System.out.println(Extra.formatText("(+)INNOVATION    (+)MORALE    (-)FUND"));
    }

    @Override
    public void applyEffectModerate(City city, int level) {
        pointAllocation(level, 2, city, "innovation");
        Extra.centerText("Private sponsorship helps fund the tech festival, supporting innovation.");
        System.out.println(Extra.formatText("(+)INNOVATION    (-)FUND"));
    }

    @Override
    public void applyEffectNone(City city, int level) {
        pointAllocation(level, 3, city, "innovation", "morale");
        Extra.centerText("Declining the festival dampens morale and innovation.");
        System.out.println(Extra.formatText("(-)INNOVATION    (-)MORALE"));
    }
}


