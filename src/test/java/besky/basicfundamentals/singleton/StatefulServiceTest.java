package besky.basicfundamentals.singleton;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread1: 사용자1이 1원 주문
        int price1 = 1;
        statefulService1.order("name1", price1);
        // Thread2: 사용자2가 2원 주문
        statefulService2.order("name2", 2);

        // Thread1: 사용자1이 주문 금액 조회
        int orderedPrice1 = statefulService1.getPrice();

        // 짜잔~
        assertThat(orderedPrice1).isNotEqualTo(price1);
    }

    static class TestConfig{
        @Bean
        public StatefulService service(){
            return new StatefulService();
        }
    }
}