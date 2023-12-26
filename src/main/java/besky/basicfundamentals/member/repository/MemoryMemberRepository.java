package besky.basicfundamentals.member.repository;

import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.domain.MemberDto;
import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    private static final Map<Long, Member> storage = new HashMap<>();
    // use ConCurrentHashMap in real project

    @Override
    public Member save(MemberDto memberDto) {
        Member member = Member.of(memberDto);
        storage.put(memberDto.id(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return storage.get(id);
    }
}
