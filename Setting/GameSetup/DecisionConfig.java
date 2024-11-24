package GameSetup;

public class DecisionConfig {

    public static class PointChange {
        int maxPrimary, minPrimary, maxSecondary, minSecondary, maxFund, minFund;

        public PointChange(int maxPrimary, int minPrimary, int maxSecondary, int minSecondary, int maxFund, int minFund) {
            this.maxPrimary = maxPrimary;
            this.minPrimary = minPrimary;
            this.maxSecondary = maxSecondary;
            this.minSecondary = minSecondary;
            this.maxFund = maxFund;
            this.minFund = minFund;
        }
    }

    public static PointChange getPointChanges(int level, int decision) {
        return switch (level) {
            case 1 -> switch (decision) {
                case 1 -> new PointChange(20, 15, 5, 1, -15, -12);
                case 2 -> new PointChange(15, 10, 0, 0, -13, -10);
                case 3 -> new PointChange(-15, -10, -3, -1, 0, 0);
                default -> throw new IllegalArgumentException("Invalid decision");
            };
            case 2 -> switch (decision) {
                case 1 -> new PointChange(18, 14, 4, 1, -16, -12);
                case 2 -> new PointChange(13, 8, 0, 0, -14, -10);
                case 3 -> new PointChange(-15, -10, -3, -2, 0, 0);
                default -> throw new IllegalArgumentException("Invalid decision");
            };
            case 3 -> switch (decision) {
                case 1 -> new PointChange(16, 10, 3, 1, -17, -14);
                case 2 -> new PointChange(15, 10, 0, 0, -15, -12);
                case 3 -> new PointChange(-18, -13, -4, -2, 0, 0);
                default -> throw new IllegalArgumentException("Invalid decision");
            };
            default -> throw new IllegalArgumentException("Invalid level");
        };
    }
}
