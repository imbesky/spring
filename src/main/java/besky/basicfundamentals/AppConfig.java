package besky.basicfundamentals;

import besky.basicfundamentals.discount.RateDiscountPolicy;
import besky.basicfundamentals.member.repository.MemoryMemberRepository;
import besky.basicfundamentals.member.service.MemberService;
import besky.basicfundamentals.member.service.MemberServiceImpl;
import besky.basicfundamentals.order.OrderService;
import besky.basicfundamentals.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(getMemberRepository());
    }

    private MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }

    private RateDiscountPolicy getDiscountPolicy() {
        return new RateDiscountPolicy();
    }

}
