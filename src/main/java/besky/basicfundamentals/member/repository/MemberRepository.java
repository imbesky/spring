package besky.basicfundamentals.member.repository;

import besky.basicfundamentals.member.domain.Member;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);
}
