package besky.basicfundamentals.xml;

import static org.assertj.core.api.Assertions.assertThat;

import besky.basicfundamentals.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    @Test
    void xmlAppContext(){
        ApplicationContext a = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = a.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
