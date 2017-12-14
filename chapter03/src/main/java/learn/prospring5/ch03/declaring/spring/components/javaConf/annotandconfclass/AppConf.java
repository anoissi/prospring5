package learn.prospring5.ch03.declaring.spring.components.javaConf.annotandconfclass;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"learn.prospring5.ch03.declaring.spring.components.annot.impl"})
@Configuration
public class AppConf {
}
