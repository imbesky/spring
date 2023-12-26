package besky.basicfundamentals.member.service;

import besky.basicfundamentals.member.domain.Member;

public interface MemberService {
    Member join(Member member);
    Member findMemberById(Long id);
}
