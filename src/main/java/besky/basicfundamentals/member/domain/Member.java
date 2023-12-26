package besky.basicfundamentals.member.domain;

public class Member {
    private final Long id;
    private final String name;
    private final Grade grade;

    private Member(Long id, String  name, Grade grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public static Member of(MemberDto memberDto){
        return new Member(memberDto.id(), memberDto.name(), memberDto.grade());
    }

}
