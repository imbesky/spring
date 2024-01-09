package besky.basicfundamentals.scan;

import static org.assertj.core.api.Assertions.assertThat;

import besky.basicfundamentals.AutoAppConfig;
import besky.basicfundamentals.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigScanTest {
    @Test
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
