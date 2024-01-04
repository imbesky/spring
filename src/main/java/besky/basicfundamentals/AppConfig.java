package besky.basicfundamentals;

import besky.basicfundamentals.discount.RateDiscountPolicy;
import besky.basicfundamentals.member.repository.MemoryMemberRepository;
import besky.basicfundamentals.member.service.MemberService;
import besky.basicfundamentals.member.service.MemberServiceImpl;
import besky.basicfundamentals.order.OrderService;
import besky.basicfundamentals.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }

    @Bean
    public RateDiscountPolicy getDiscountPolicy() {
        return new RateDiscountPolicy();
    }

}
