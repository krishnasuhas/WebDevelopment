package model;

import lombok.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Circle implements Shape, ApplicationContextAware, BeanNameAware {
    int radius;
    String colour;
    Point point;

    public void draw() {
        System.out.println(this);
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
//        context.getBean(""); // in case if you want get any bean by yourself
    }

    public void setBeanName(String beanName) {
//        System.out.println("Bean Name is "+ beanName); // in case if you want to get bean name
    }

    @PostConstruct
    void postConstruct(){
//        System.out.println("init called from circle");
    }

    @PreDestroy
    void preDestroy() {
//        System.out.println("destroy called from circle");
    }
}
