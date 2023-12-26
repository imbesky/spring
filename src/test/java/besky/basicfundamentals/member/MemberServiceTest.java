package besky.basicfundamentals.member;

import static org.assertj.core.api.Assertions.assertThat;

import besky.basicfundamentals.member.domain.Grade;
import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.domain.MemberDto;
import besky.basicfundamentals.member.service.MemberService;
import besky.basicfundamentals.member.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    @Test
    @DisplayName("member join test")
    void join(){
        MemberDto memberDto = new MemberDto(0L, "name", Grade.VIP);

        Member joinedMember = memberService.join(memberDto);
        Member findedMember = memberService.findMemberById(0L);

        assertThat(joinedMember == findedMember).isTrue();
    }
}
