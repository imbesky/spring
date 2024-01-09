package besky.basicfundamentals.member.repository;

import besky.basicfundamentals.member.domain.Member;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class MemoryMemberRepository implements MemberRepository{
    private static final Map<Long, Member> storage = new HashMap<>();
    // use ConCurrentHashMap in real project

    @Override
    public Member save(Member member) {
        storage.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return storage.get(id);
    }
}
