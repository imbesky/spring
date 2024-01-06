package besky.basicfundamentals.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import besky.basicfundamentals.AppConfig;
import besky.basicfundamentals.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 조회: 호출시마다 객체를 생성
        MemberService memberService = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        // 참조값이 상이함을 확인
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);
        assertThat(memberService).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴 적용한 객체 사용")
    void singleton(){
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        // isSameAs() == `==`
        assertThat(singletonService).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같음을 확인
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService).isSameAs(memberService2);
    }
}
