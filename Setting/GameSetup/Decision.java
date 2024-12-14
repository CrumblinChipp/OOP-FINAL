package GameSetup;

public abstract class Decision {
    String description;

    public Decision(String description) {
        this.description = description;
    }

    public abstract void applyEffect(City city, int level, int choice);

    private static int getRandomInRange(int max, int min) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    protected static void pointAllocation(int level, int decision, City city, String primaryAttribute, String secondaryAttribute) {
        var changes = DecisionConfig.getPointChanges(level, decision);
        int primaryChange = getRandomInRange(changes.maxPrimary, changes.minPrimary);
        int secondaryChange = getRandomInRange(changes.maxSecondary, changes.minSecondary);
        int fundChange = getRandomInRange(changes.maxFund, changes.minFund);

        city.setAttribute(primaryAttribute, city.getAttribute(primaryAttribute) + primaryChange);
        city.setAttribute(secondaryAttribute, city.getAttribute(secondaryAttribute) + secondaryChange);
        city.setAttribute("fund", city.getAttribute("fund") + fundChange);
    }

    protected static void printDecisionOutcome(String effectSummary, String details) {
        Extra.centerText(effectSummary);
        System.out.println(Extra.formatText(details));
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────┘");
    }


    private static Decision getDecisionObject(int scenarioNumber) {
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
        Decision decision = getDecisionObject(randomScenario);
        decision.applyEffect(city, level, choice);
    }
    public String getDescription() {
        return description;
    }
}
// HOSPITAL CRISIS
class DecisionScenario1 extends Decision {
    public DecisionScenario1(String description) {
        super(description);
    }
    @Override
    public void applyEffect(City city, int level, int choice) {
        switch(choice){
            case 1 -> {
                pointAllocation(level, 1, city, "healthcare", "morale");
                printDecisionOutcome("New hospitals are under construction to handle the influx of patients! ","(+)HEALTH    (-)FUND    (+)MORALE");
            }

            case 2 -> {
                pointAllocation(level, 2, city, "healthcare", "morale");
                printDecisionOutcome("Existing hospitals receive additional funding to improve services.","(+)HEALTH    (-)FUND");
            }

            case 3 -> {
                pointAllocation(level, 3, city, "healthcare", "morale");
                printDecisionOutcome("The situation worsens as healthcare demands continue to rise.", "(-)HEALTH    (-)MORALE");
            }
        }
    }
}

// DISASTER CRISIS
class DecisionScenario2 extends Decision {
    public DecisionScenario2(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "innovation", "morale");
                printDecisionOutcome("Full disaster relief fund allocated. Citizens are relieved!", "(+)INNOVATION    (-)FUND    (+)MORALE");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "innovation", "fund");
                printDecisionOutcome("Limited fund provided. Essential repairs begin, but citizens remain concerned.", "(+)INNOVATION    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "innovation", "morale");
                printDecisionOutcome("No action taken. Citizens are frustrated, and infrastructure worsens.", "(-)INNOVATION    (-)MORALE");
            }
        }
    }
}

// ENVIRONMENTAL CRISIS
class DecisionScenario3 extends Decision {
    public DecisionScenario3(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "environment", "healthcare");
                printDecisionOutcome("Environmental standards are now stricter, and pollution levels have decreased.", "(+)ENVIRONMENT    (+)HEALTHCARE    (-)FUND");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "environment", "fund");
                printDecisionOutcome("Some new regulations are in place, aiming to reduce pollution.", "(+)ENVIRONMENT    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "environment", "morale");
                printDecisionOutcome("Pollution continues, and citizens grow more concerned about the environment.", "(-)ENVIRONMENT    (-)MORALE");
            }
        }
    }
}

// POLICE CRISIS
class DecisionScenario4 extends Decision {
    public DecisionScenario4(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "morale", "healthcare");
                printDecisionOutcome("Police funding and community programs are ramped up. Citizens feel relief and hope.", "(+)MORALE    (+)HEALTHCARE    (-)FUND");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "morale", "fund");
                printDecisionOutcome("Police funding increases, boosting security, but some citizens feel overlooked.", "(+)MORALE    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "morale", "healthcare");
                printDecisionOutcome("Tensions rise as no action is taken. The city struggles with growing unease.", "(-)MORALE    (-)HEALTHCARE");
            }
        }
    }
}

// ECONOMIC STIMULUS CRISIS
class DecisionScenario5 extends Decision {
    public DecisionScenario5(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "innovation", "morale");
                printDecisionOutcome("The stimulus package injects capital into the economy, creating jobs and reviving businesses.", "(+)INNOVATION    (+)MORALE    (-)FUND");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "innovation", "fund");
                printDecisionOutcome("Small businesses receive tax breaks, easing financial burdens and encouraging growth.", "(+)INNOVATION    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "innovation", "morale");
                printDecisionOutcome("The economy struggles as citizens grow frustrated with the lack of intervention.", "(-)INNOVATION    (-)MORALE");
            }
        }
    }
}

// EDUCATION REFORM CRISIS
class DecisionScenario6 extends Decision {
    public DecisionScenario6(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "education", "morale");
                printDecisionOutcome("The city embarks on a major educational transformation, hoping to reshape the future for its youth.", "(+)EDUCATION    (+)MORALE    (-)FUND");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "education", "morale");
                printDecisionOutcome("The city takes steps to improve education gradually, balancing progress with stability.", "(+)EDUCATION    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "education", "morale");
                printDecisionOutcome("The city overlooks the teachers' demands, leaving the system as it is for now.", "(-)EDUCATION    (-)MORALE");
            }
        }
    }
}

// EDUCATION TECHNOLOGY
class DecisionScenario7 extends Decision {
    public DecisionScenario7(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "education", "innovation");
                printDecisionOutcome("The investment in digital learning resources boosts access to education and encourages student engagement.", "(+)EDUCATION    (+)INNOVATION    (-)FUND");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "education", "morale");
                printDecisionOutcome("Scholarships help more students access higher education, boosting morale and skills.", "(+)EDUCATION    (+)MORALE    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "education", "morale");
                printDecisionOutcome("The lack of support for educational resources leads to increased dropout rates and public discontent.", "(-)EDUCATION    (-)MORALE");
            }
        }
    }
}

// ENVIRONMENT POLLUTION
class DecisionScenario8 extends Decision {
    public DecisionScenario8(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "environment", "healthcare");
                printDecisionOutcome("Stricter regulations reduce pollution, improving public health and environmental quality.", "(+)ENVIRONMENT    (+)HEALTHCARE    (-)FUND");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "environment", "morale");
                printDecisionOutcome("The cleanup and reforestation program beautifies the city and raises morale.", "(+)ENVIRONMENT    (+)MORALE    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "environment", "morale");
                printDecisionOutcome("Pollution worsens, negatively impacting both the environment and public morale.", "(-)ENVIRONMENT    (-)MORALE");
            }
        }
    }
}

// HOSPITAL FACILITY
class DecisionScenario9 extends Decision {
    public DecisionScenario9(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "healthcare", "morale");
                printDecisionOutcome("Expanding healthcare infrastructure provides better access to medical care, boosting public morale.", "(+)HEALTHCARE    (+)MORALE    (-)FUND");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "healthcare", "fund");
                printDecisionOutcome("Increasing subsidies for essential medicines improves access to healthcare with moderate costs.", "(+)HEALTHCARE    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "healthcare", "morale");
                printDecisionOutcome("Healthcare remains inadequate, leading to public dissatisfaction and a decline in morale.", "(-)HEALTHCARE    (-)MORALE");
            }
        }
    }
}

// INNOVATION FESTIVAL
class DecisionScenario10 extends Decision {
    public DecisionScenario10(String description) {
        super(description);
    }

    @Override
    public void applyEffect(City city, int level, int choice) {
        switch (choice) {
            case 1 -> {
                pointAllocation(level, 1, city, "innovation", "morale");
                printDecisionOutcome("The tech festival attracts talent, boosting morale and innovation.", "(+)INNOVATION    (+)MORALE    (-)FUND");
            }
            case 2 -> {
                pointAllocation(level, 2, city, "innovation", "morale");
                printDecisionOutcome("Private sponsorship helps fund the tech festival, supporting innovation.", "(+)INNOVATION    (-)FUND");
            }
            case 3 -> {
                pointAllocation(level, 3, city, "innovation", "morale");
                printDecisionOutcome("Declining the festival dampens morale and innovation.", "(-)INNOVATION    (-)MORALE");
            }
        }
    }
}

