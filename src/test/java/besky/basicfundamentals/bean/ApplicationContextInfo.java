package besky.basicfundamentals.bean;

import besky.basicfundamentals.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfo {

    AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 스프링 빈 출력")
    void printAll(){
        String[] beanDefinitionNames = a.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames){
            Object bean = a.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }
    @Test
    @DisplayName("어플리케이션 스프링 빈 출력")
    void printApplicationBean(){
        String[] beanDefinitionNames = a.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = a.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = a.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }

}

