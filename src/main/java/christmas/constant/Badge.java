package christmas.constant;

public enum Badge {
    SANTA(20_000),
    TREE(10_000),
    STAR(5_000),
    NONE(0);

    private final int threshold;

    Badge(int threshold) {
        this.threshold = threshold;
    }

    public static Badge getEligibleBadge(int totalBenefitAmount) {
        for (Badge badge : values()) {
            if (totalBenefitAmount >= badge.threshold) {
                return badge;
            }
        }
        throw new IllegalStateException("NONE Badge를 포함해 뱃지는 무조건 리턴되어야 합니다.");
    }
}
