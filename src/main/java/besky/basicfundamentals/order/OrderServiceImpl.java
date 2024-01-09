package besky.basicfundamentals.order;

import besky.basicfundamentals.discount.DiscountPolicy;
import besky.basicfundamentals.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long id, String item, int itemPrice) {
        return new Order(id, "item", 10_000, discountPolicy.discountValue(memberRepository.findById(id), 10_000));
    }

    //for test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
