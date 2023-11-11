package christmas.constant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BadgeTest {

    @DisplayName("혜택금액에 따라 뱃지를 올바르게 반환하는지 테스트")
    @Test
    void getEligibleBadge() {
        int totalBenefitAmount = 20000;
        assertThat(Badge.getEligibleBadge(totalBenefitAmount)).isEqualTo(Badge.SANTA);

        totalBenefitAmount = 10000;
        assertThat(Badge.getEligibleBadge(totalBenefitAmount)).isEqualTo(Badge.TREE);

        totalBenefitAmount = 5000;
        assertThat(Badge.getEligibleBadge(totalBenefitAmount)).isEqualTo(Badge.STAR);

        totalBenefitAmount = 4900;
        assertThat(Badge.getEligibleBadge(totalBenefitAmount)).isEqualTo(Badge.NONE);
    }
}