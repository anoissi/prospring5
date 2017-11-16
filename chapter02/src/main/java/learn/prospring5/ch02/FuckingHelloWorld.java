package learn.prospring5.ch02;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FuckingHelloWorld {
    ApplicationContext ctx = new ClassPathXmlApplicationContext
            ("spring/app-context.xml");

    public static void main( final String[] args ){
        ApplicationContext ctx = new ClassPathXmlApplicationContext
                ("spring/app-context.xml");
        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}
