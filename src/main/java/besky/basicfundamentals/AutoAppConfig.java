package besky.basicfundamentals;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type =  FilterType.ANNOTATION, classes = Configuration.class)
        // 스프링 빈으로 등록하지 않을 것을 지정
        // 충돌이 나지 않도록 @Configuration 을 스프링 빈 등록 대상에서 제외해준 것
)
public class AutoAppConfig {

}
