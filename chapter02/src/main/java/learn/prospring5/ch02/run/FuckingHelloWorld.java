package learn.prospring5.ch02.run;
import learn.prospring5.ch02.beans.dec.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



// Using a xml configuration file (without spring annotations which is invoked in spring 3.0  )

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
