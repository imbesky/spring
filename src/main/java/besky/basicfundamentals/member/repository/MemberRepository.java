package besky.basicfundamentals.member.repository;

import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.domain.MemberDto;

public interface MemberRepository {
    Member save(MemberDto member);
    Member findById(Long id);
}
