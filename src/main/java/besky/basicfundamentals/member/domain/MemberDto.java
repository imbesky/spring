package besky.basicfundamentals.member.domain;

public record MemberDto(
        Long id,
        String name,
        Grade grade
) {
}
