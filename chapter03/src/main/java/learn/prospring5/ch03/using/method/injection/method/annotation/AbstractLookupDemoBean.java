package learn.prospring5.ch03.using.method.injection.method.annotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public class AbstractLookupDemoBean implements DemoBean {

    @Lookup("singer")
    public Singer getMySinger() {
        return null; // overriden dynamically
    }
    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}