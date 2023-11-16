package christmas.constant;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final int threshold;
    private final String name;

    Badge(String name, int threshold) {
        this.threshold = threshold;
        this.name = name;
    }

    public static Badge getEligibleBadge(int totalBenefitAmount) {
        return Arrays.stream(values())
                .filter(badge -> totalBenefitAmount >= badge.threshold)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("NONE Badge를 포함해 뱃지는 무조건 리턴되어야 합니다."));
    }

    public String getName() {
        return name;
    }
}
