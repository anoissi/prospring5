package learn.prospring5.ch03.declaring.spring.components.injectionfield;

import org.springframework.context.support.GenericXmlApplicationContext;

public class FieldInjection {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext();
        ctx.load("classpath:spring/injection-field/app-context.xml");
        ctx.refresh();
        Singer singerBean = ctx.getBean(Singer.class);
        singerBean.sing();
        ctx.close();
    }
}