package learn.prospring5.ch03.ioc.injava.di.cons;

import learn.prospring5.ch03.ioc.injava.dl.cdl.impl.Dependency;

public class SetterInjection {

    private Dependency dependency;

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public String toString() {
        return dependency.toString();
    }
}
