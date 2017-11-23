package learn.prospring5.ch03.ioc.di.cons;

import learn.prospring5.ch03.ioc.dl.cdl.impl.Dependency;

public class Demo {

    public static void main(String[] args) {
        Dependency dependency = new Dependency();
        ConstructorInjection constructorInjection = new ConstructorInjection(dependency);
        System.out.println(constructorInjection.toString() + " and " + ConstructorInjection.class.getCanonicalName());
    }
}
