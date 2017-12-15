package learn.prospring5.ch03.declaring.spring.components.intro.annotation.beans.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"learn.prospring5.ch03.declaring.spring.components.annotation.beans.impl"})
@Configuration
public class AnnotationConfiguration {

}
