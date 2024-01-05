package besky.basicfundamentals.bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import besky.basicfundamentals.member.repository.MemberRepository;
import besky.basicfundamentals.member.repository.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameTypeBeanFindTest {
    AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(DemoConfig.class);

    @Test
    @DisplayName("같은 타입의 빈이 둘 이상 있는 경우 중복 오류 발생")
    void duplicationError(){
        assertThrows(NoUniqueBeanDefinitionException.class, () -> a.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("같은 타입의 빈이 둘 이상 있는 경우 빈 이름을 지정해 조회")
    void inquireDuplicatedBeansByName(){
        MemberRepository memberRepository = a.getBean("firstMemberRepository", MemberRepository.class);

        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("같은 타입의 빈이 둘 이상 있는 경우 타입으로 조회")
    void inquireDuplicatedBeansByType(){
        Map<String, MemberRepository> beans = a.getBeansOfType(MemberRepository.class);

        for (String bean : beans.keySet()){
            System.out.println("name = " + bean);
        }
    }

    @Configuration
    static class DemoConfig {
        @Bean
        public MemberRepository firstMemberRepository(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository secondMemberRepository(){
            return new MemoryMemberRepository();
        }
    }
}
