package learn.prospring5.ch03.declaring.spring.components.intro.annot.impl;

import learn.prospring5.ch03.declaring.spring.components.intro.annot.dec.MessageProvider;
import org.springframework.stereotype.Component;

@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}