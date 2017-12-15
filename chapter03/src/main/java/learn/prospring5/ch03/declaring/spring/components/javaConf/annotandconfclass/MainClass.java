package learn.prospring5.ch03.declaring.spring.components.javaConf.annotandconfclass;

import learn.prospring5.ch03.declaring.spring.components.intro.annot.dec.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext
                (AppConf.class);
        MessageRenderer mr = ctx.getBean("render", MessageRenderer.class);
        mr.render();
    }
}
