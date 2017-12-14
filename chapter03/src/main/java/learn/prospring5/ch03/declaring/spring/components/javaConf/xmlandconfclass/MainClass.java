package learn.prospring5.ch03.declaring.spring.components.javaConf.xmlandconfclass;

import learn.prospring5.ch02.beans.dec.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext
                (Appconf.class);
        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();
    }

}
