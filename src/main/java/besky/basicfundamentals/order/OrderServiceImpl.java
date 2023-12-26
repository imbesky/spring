package besky.basicfundamentals.order;

import besky.basicfundamentals.discount.DiscountPolicy;
import besky.basicfundamentals.discount.FixDiscountPolicy;
import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.repository.MemberRepository;
import besky.basicfundamentals.member.repository.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long id, String item, int itemPrice) {
        return new Order(id, "item", 10_000, discountPolicy.discountValue(memberRepository.findById(id), 10_000));
    }
}
