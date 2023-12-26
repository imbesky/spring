package besky.basicfundamentals.discount;

import besky.basicfundamentals.member.constant.Grade;
import besky.basicfundamentals.member.domain.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private final int fixedDiscountAmount = 1_000;

    @Override
    public int discountValue(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return fixedDiscountAmount;
        }
        return 0;
    }
}
