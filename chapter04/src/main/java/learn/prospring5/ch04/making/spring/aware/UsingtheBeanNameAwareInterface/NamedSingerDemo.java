package learn.prospring5.ch04.making.spring.aware.UsingtheBeanNameAwareInterface;

import org.springframework.context.support.GenericXmlApplicationContext;

public class NamedSingerDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext();
        ctx.load("classpath:makingYourBeanSpringAware/app-context.xml");
        ctx.refresh();
        NamedSinger bean = (NamedSinger) ctx.getBean("johnMayer");
        bean.sing();
        ctx.close();
    }
}