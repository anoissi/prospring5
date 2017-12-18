package learn.prospring5.ch04.hooking.into.bean.creation.DeclaringanInitializationMethodUsing;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static learn.prospring5.ch04.hooking.into.bean.creation.ExecutingaMethodWhenaBeaIsCreated.Singer.getBean;

public class TestSingConfDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(SingerConfigDemo.SingerConfig.class);
        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);
        ctx.close();
    }
}
