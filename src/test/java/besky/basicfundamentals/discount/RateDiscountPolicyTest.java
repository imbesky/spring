package besky.basicfundamentals.discount;

import static org.assertj.core.api.Assertions.assertThat;

import besky.basicfundamentals.member.constant.Grade;
import besky.basicfundamentals.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 할인 금액 테스트")
    void vipDiscount(){
        Member member = new Member(0L, "name", Grade.VIP);

        int discount = discountPolicy.discountValue(member, 10_000);

        assertThat(discount).isEqualTo(1_000);
    }

    @Test
    @DisplayName("일반 회원 할인 금액 테스트")
    void standardDiscount(){
        Member member = new Member(0L, "name", Grade.STANDARD);

        int discount = discountPolicy.discountValue(member, 10_000);

        assertThat(discount).isEqualTo(0);
    }

}