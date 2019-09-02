package model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Rectangle implements Shape {

    @Autowired
    Point zeroPoint;

    @Autowired
    Point point2;

    @Autowired
    Point point3;

    @Resource(name = "point4")
    Point point;

    public void draw() {
        System.out.println(this);
    }
}
