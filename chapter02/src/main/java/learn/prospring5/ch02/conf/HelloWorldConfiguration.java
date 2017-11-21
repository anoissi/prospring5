package learn.prospring5.ch02.conf;

import learn.prospring5.ch02.beans.dec.MessageProvider;
import learn.prospring5.ch02.beans.dec.MessageRenderer;
import learn.prospring5.ch02.beans.impl.HelloWorldMessageProvider;
import learn.prospring5.ch02.beans.impl.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
    // equivalent to <bean id="provider" class=".."/>
    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }
    // equivalent to  <bean id="renderer" class=".."/>
    @Bean
    public MessageRenderer renderer(){
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}