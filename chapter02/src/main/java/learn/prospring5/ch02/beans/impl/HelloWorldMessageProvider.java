package learn.prospring5.ch02.beans.impl;

import learn.prospring5.ch02.beans.dec.MessageProvider;

public class HelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}