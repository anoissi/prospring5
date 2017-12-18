package learn.prospring5.ch04.hooking.into.bean.creation.UsingtheJSR;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class SingerWithJSR250 {
    private static final  String DEFAULT_NAME  =  "Eric Clapton";
    private String name;
    private int age = Integer.MIN_VALUE;
    public void setName(String name)  {
        this.name = name;
    }
    public void setAge(int  age) {
        this.age = age;
    }
    @PostConstruct
    public void init() throws Exception {
        System.out.println("Initializing bean");
        if (name  ==  null) {
            System.out.println("Using default name");
            name =  DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " +
                            SingerWithJSR250.class);
        }
    }
    public String toString()  {
        return "\tName: " + name + "\n\tAge: " +  age;
    }
    public static void main(String... args) {
        GenericXmlApplicationContext  ctx  =
                new GenericXmlApplicationContext();
        ctx.load("classpath:hookingIntoBeanCreation/app-context-jsr.xml");
        ctx.refresh();
        getBean("singerOne", ctx);
        getBean("singerTwo", ctx);
        getBean("singerThree", ctx);
        ctx.close();
    }
    public static SingerWithJSR250 getBean(String beanName,
                                           ApplicationContext ctx) {
        try {
            SingerWithJSR250 bean  =
                    (SingerWithJSR250) ctx.getBean(beanName);
            System.out.println(bean);
            return  bean;
        } catch (BeanCreationException ex) {
            System.out.println("An error occured in bean configuration: "
                    + ex.getMessage());
            return null;
        }
    }
}