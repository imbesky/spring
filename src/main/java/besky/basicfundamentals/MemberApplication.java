package besky.basicfundamentals;

import besky.basicfundamentals.member.constant.Grade;
import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApplication {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member joinedMember = memberService.join(new Member(0L, "name", Grade.VIP));
        Member findedMember = memberService.findMemberById(0L);

        System.out.println(
                joinedMember == findedMember
        );
    }
}
