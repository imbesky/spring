package besky.basicfundamentals.order;

import static org.assertj.core.api.Assertions.assertThat;

import besky.basicfundamentals.AppConfig;
import besky.basicfundamentals.member.constant.Grade;
import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.service.MemberService;
import besky.basicfundamentals.member.service.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("create order")
    void order(){
        Long id = 0L;
        Member member = memberService.join(new Member(id, "name", Grade.VIP));

        assertThat(member.getId()).isEqualTo(id);
        assertThat(member.getName()).isEqualTo("name");
    }

    @Test
    @DisplayName("final value")
    void value(){
        Long id = 0L;
        Member member = memberService.join(new Member(id, "name", Grade.VIP));
        Order order = orderService.createOrder(id, "item", 10_000);

        assertThat(order.finalPrice()).isEqualTo(9_000);
    }
}
