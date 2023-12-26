package besky.basicfundamentals.member.service;

import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.domain.MemberDto;

public interface MemberService {
    Member join(MemberDto memberDto);
    Member findMemberById(Long id);
}
