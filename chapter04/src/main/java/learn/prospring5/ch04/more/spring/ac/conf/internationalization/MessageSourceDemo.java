package learn.prospring5.ch04.more.spring.ac.conf.internationalization;

import java.util.Locale;
import org.springframework.context.support.GenericXmlApplicationContext;
public class MessageSourceDemo  {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:moreSpringConf/internationalization/app-intern.xml");
        ctx.refresh();
        Locale english = Locale.ENGLISH;
        Locale german = new Locale("de", "DE");
        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null,  german));
        System.out.println(ctx.getMessage("nameMsg", new Object[]
                { "John", "Mayer" }, english));
        System.out.println(ctx.getMessage("nameMsg", new Object[]
                { "John", "Mayer" }, german));
        ctx.close();
    }
}
