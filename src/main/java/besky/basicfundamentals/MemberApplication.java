package besky.basicfundamentals;

import besky.basicfundamentals.member.domain.Grade;
import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.domain.MemberDto;
import besky.basicfundamentals.member.service.MemberService;
import besky.basicfundamentals.member.service.MemberServiceImpl;

public class MemberApplication {
    public static void main(String[] args) {
        //violate DIP by depending on both abstraction and detail
        MemberService memberService = new MemberServiceImpl();

        MemberDto memberDto = new MemberDto(0L, "name", Grade.VIP);

        Member joinedMember = memberService.join(memberDto);
        Member findedMember = memberService.findMemberById(0L);

        System.out.println(
                joinedMember == findedMember
        );
    }
}
