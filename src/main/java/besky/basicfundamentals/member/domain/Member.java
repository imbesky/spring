package besky.basicfundamentals.member.domain;

import besky.basicfundamentals.member.constant.Grade;

public class Member {
    private final Long id;
    private final String name;
    private final Grade grade;

    public Member(Long id, String  name, Grade grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }
}
