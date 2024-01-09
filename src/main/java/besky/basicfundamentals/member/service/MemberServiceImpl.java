package besky.basicfundamentals.member.service;

import besky.basicfundamentals.member.domain.Member;
import besky.basicfundamentals.member.repository.MemberRepository;
import besky.basicfundamentals.member.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    //violate DIP by depending on both abstraction and detail
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemoryMemberRepository memoryMemberRepository) {
        this.memberRepository = memoryMemberRepository;
    }

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    //for test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
