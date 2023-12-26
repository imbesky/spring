package besky.basicfundamentals.discount;

import besky.basicfundamentals.member.domain.Member;

public interface DiscountPolicy {
    int discountValue(Member member, int price);
}
