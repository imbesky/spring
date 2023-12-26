package besky.basicfundamentals.member.service;

import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.repository.MemberRepository;
import besky.basicfundamentals.member.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{
    //violate DIP by depending on both abstraction and detail
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findById(id);
    }
}
