package besky.basicfundamentals.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import besky.basicfundamentals.AppConfig;
import besky.basicfundamentals.member.repository.MemberRepository;
import besky.basicfundamentals.member.service.MemberServiceImpl;
import besky.basicfundamentals.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigureSingletonTest {
    @Test
    void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        // 원래는 구체 타입으로 꺼내지 말 것
//        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberServiceRepository = memberService.getMemberRepository();
        MemberRepository orderServiceRepository = orderService.getMemberRepository();

        assertThat(memberServiceRepository).isSameAs(orderServiceRepository);
//        assertThat(memberServiceRepository).isSameAs(memberRepository);
    }

    @Test
    void configuration(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // AppConfig$$SpringCGLIB$$0
    }
}
