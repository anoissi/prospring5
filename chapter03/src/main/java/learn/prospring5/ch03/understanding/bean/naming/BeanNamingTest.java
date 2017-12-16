package learn.prospring5.ch03.understanding.bean.naming;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

public class BeanNamingTest {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/undrestanding-bean-naming/app-context.xml");
        ctx.refresh();
        Map<String,String> beans = ctx.getBeansOfType(String.class);
        beans.entrySet().stream().forEach(b -> System.out.println(b.getKey()));
        ctx.close();
    }
}