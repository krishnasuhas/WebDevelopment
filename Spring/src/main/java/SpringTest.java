import model.Shape;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
        Shape shape;
//      shape = new Triangle(); //Without Spring
//      BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("spring.xml")); //methods are deprecated
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.registerShutdownHook();
        shape = (Shape) context.getBean("triangle");
        shape.draw();

        shape = (Shape) context.getBean("circle");
        shape.draw();

        shape = (Shape) context.getBean("rectangle");
        shape.draw();
    }
}
