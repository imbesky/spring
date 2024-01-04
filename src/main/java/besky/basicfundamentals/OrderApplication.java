package besky.basicfundamentals;

import besky.basicfundamentals.member.constant.Grade;
import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.service.MemberService;
import besky.basicfundamentals.order.Order;
import besky.basicfundamentals.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long id = 0L;
        Member member = memberService.join(new Member(id, "name", Grade.VIP));

        Order order = orderService.createOrder(id, "item", 10_000);
    }
}
