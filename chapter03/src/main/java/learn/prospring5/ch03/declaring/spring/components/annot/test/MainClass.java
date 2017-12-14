package learn.prospring5.ch03.declaring.spring.components.annot.test;

import learn.prospring5.ch03.declaring.spring.components.annot.dec.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/using-annot/app-context.xml");
        ctx.refresh();
        MessageRenderer messageRenderer = ctx.getBean("render",
                MessageRenderer.class);
        messageRenderer.render();
        ctx.close();
    }
}
