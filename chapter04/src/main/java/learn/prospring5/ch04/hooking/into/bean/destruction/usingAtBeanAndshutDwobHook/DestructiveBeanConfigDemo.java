package learn.prospring5.ch04.hooking.into.bean.destruction.usingAtBeanAndshutDwobHook;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

public class DestructiveBeanConfigDemo {
    @Configuration
    static class  DestructiveBeanConfig {
        @Lazy
        @Bean(initMethod = "afterPropertiesSet", destroyMethod = "destroy")
        DestructiveBeanWithJSR250 destructiveBean() {
            DestructiveBeanWithJSR250 destructiveBean  =
                    new DestructiveBeanWithJSR250();
            destructiveBean.setFilePath(System.getProperty("java.io.tmpdir") +
                    System.getProperty("file.separator") +  "test.txt");
            return  destructiveBean;
        }
    }
    public static void main(String... args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(DestructiveBeanConfig.class);
        ctx.getBean(DestructiveBeanWithJSR250.class);
        System.out.println("Calling destroy()");
        //ctx.destroy();
        ctx.registerShutdownHook();
        System.out.println("Called destroy()");
    }
}
