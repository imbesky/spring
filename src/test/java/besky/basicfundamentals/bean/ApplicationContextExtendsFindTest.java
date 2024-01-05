package besky.basicfundamentals.bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import besky.basicfundamentals.discount.DiscountPolicy;
import besky.basicfundamentals.discount.FixDiscountPolicy;
import besky.basicfundamentals.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(DemoConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 오류 발생")
    void childDuplicateError(){
        assertThrows(NoUniqueBeanDefinitionException.class, () -> a.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 이름 지정")
    void childDuplicateName(){
        DiscountPolicy rateDiscountPolicy = a.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 특정 하위 타입으로 조회")
    void childDuplicateSubType(){
        RateDiscountPolicy bean = a.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllByParentType(){
        Map<String, DiscountPolicy> beans = a.getBeansOfType(DiscountPolicy.class);
        assertThat(beans.size()).isEqualTo(2);
        for(String bean : beans.keySet()){
            System.out.println("name = " + bean);
        }
    }

    @Test
    @DisplayName("Object 타입으로 모두 조회")
    void findAllByObjectType(){
        Map<String, Object> beans = a.getBeansOfType(Object.class);
        for(String bean : beans.keySet()){
            System.out.println("name = " + bean);
        }
    }

    @Configuration
    static class DemoConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
