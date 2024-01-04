package besky.basicfundamentals;

import besky.basicfundamentals.member.constant.Grade;
import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.service.MemberService;
import besky.basicfundamentals.member.service.MemberServiceImpl;
import besky.basicfundamentals.order.Order;
import besky.basicfundamentals.order.OrderService;
import besky.basicfundamentals.order.OrderServiceImpl;

public class OrderApplication {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long id = 0L;
        Member member = memberService.join(new Member(id, "name", Grade.VIP));

        Order order = orderService.createOrder(id, "item", 10_000);
    }
}
