package besky.basicfundamentals.member.domain;

import besky.basicfundamentals.member.constant.Grade;

public record MemberDto(
        Long id,
        String name,
        Grade grade
) {
}
