package learn.prospring5.ch03.declaring.spring.components.intro.annotation.maintest;


import learn.prospring5.ch03.declaring.spring.components.intro.annotation.beans.dec.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestSpringAnnotation {

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/using-xml/app-context.xml");
        ctx.refresh();
        MessageRenderer messageRenderer = ctx.getBean("renderer",
                MessageRenderer.class);
        messageRenderer.render();
        ctx.close();
    }
}