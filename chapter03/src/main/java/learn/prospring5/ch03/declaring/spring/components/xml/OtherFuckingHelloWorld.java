package learn.prospring5.ch03.declaring.spring.components.xml;

import learn.prospring5.ch02.beans.dec.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OtherFuckingHelloWorld {
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext
            ("spring/using-xml/app-context.xml");

    public static void main( final String[] args ){
        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}
