package learn.prospring5.ch03.declaring.spring.components.javaConf.onlyconfclass;

import learn.prospring5.ch02.beans.dec.MessageProvider;
import learn.prospring5.ch02.beans.dec.MessageRenderer;
import learn.prospring5.ch02.beans.impl.HelloWorldMessageProvider;
import learn.prospring5.ch02.beans.impl.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconf {

    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }
    @Bean
    public MessageRenderer renderer(){
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}
