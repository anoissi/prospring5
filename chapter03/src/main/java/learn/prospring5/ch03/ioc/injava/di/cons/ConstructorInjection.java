package learn.prospring5.ch03.ioc.di.cons;

import learn.prospring5.ch03.ioc.injava.dl.cdl.impl.Dependency;

public class ConstructorInjection {
    private Dependency dependency;
    public ConstructorInjection(Dependency dependency) {
        this.dependency = dependency;
    }
    @Override
    public String toString() {
        return dependency.toString();
    }
}