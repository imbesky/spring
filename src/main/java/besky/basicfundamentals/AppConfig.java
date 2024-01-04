package besky.basicfundamentals;

import besky.basicfundamentals.discount.RateDiscountPolicy;
import besky.basicfundamentals.member.repository.MemoryMemberRepository;
import besky.basicfundamentals.member.service.MemberService;
import besky.basicfundamentals.member.service.MemberServiceImpl;
import besky.basicfundamentals.order.OrderService;
import besky.basicfundamentals.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }

}
