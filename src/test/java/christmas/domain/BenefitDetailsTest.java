package christmas.domain;

import christmas.constant.DiscountType;
import christmas.constant.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BenefitDeteails의 각각의 비즈니스 메서드 테스트")
class BenefitDetailsTest {
    BenefitDetails benefitDetails;

    @DisplayName("테스트용 BenefitDetails 생성")
    @BeforeEach
    void createTestBenefitDetails() {
        benefitDetails = new BenefitDetails();

        benefitDetails.addDiscountBenefit(DiscountType.SPECIAL_DISCOUNT, 1000);
        benefitDetails.addDiscountBenefit(DiscountType.D_DAY_DISCOUNT, 2500);
        benefitDetails.addDiscountBenefit(DiscountType.WEEKDAY_DISCOUNT, 6069);

        benefitDetails.addGiftBenefit(Menu.CHAMPAGNE, 1); // 샴페인가격 : 25000
    }
    @DisplayName("할인 혜택과 선물 혜택을 합한 값이 얼마인지 반환한다.")
    @Test
    void getTotalBenefit() {
        assertThat(benefitDetails.getTotalBenefit()).isEqualTo(34569);
    }

    @Test
    void getTotalDiscountAmount() {
        assertThat(benefitDetails.getTotalDiscountAmount()).isEqualTo(9569);
    }
}