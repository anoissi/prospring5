package learn.prospring5.ch03.declaring.spring.components.javaConf.xmlandconfclass;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:spring/usingjava/app-context.xml"})
@Configuration
public class Appconf {
}
