package model;

import lombok.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Triangle implements Shape, InitializingBean, DisposableBean {
    String type;
    List<Point> points;

    public void draw() {
        System.out.println(this);
    }

    void init() {
//        System.out.println("called init form Triangle ");
    }

    void cleanUp() {
//        System.out.println("called cleanUp form Triangle ");
    }

    public void afterPropertiesSet() throws Exception {
//        System.out.println("called afterPropertiesSet form Triangle ");
    }

    public void destroy() throws Exception {
//        System.out.println("called destroy form Triangle ");
    }
}