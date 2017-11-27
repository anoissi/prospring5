package learn.prospring5.ch03.ioc.injava.dl.cdl.demo;

import learn.prospring5.ch03.ioc.injava.dl.cdl.dec.Container;
import learn.prospring5.ch03.ioc.injava.dl.cdl.dec.ManagedComponent;
import learn.prospring5.ch03.ioc.injava.dl.cdl.impl.ContextualizedDependencyLookup;
import learn.prospring5.ch03.ioc.injava.dl.cdl.impl.DefaultContainer;

public class CDLDemo {
    public static void main(String... args) {
        Container container = new DefaultContainer();

        ManagedComponent managedComponent = new ContextualizedDependencyLookup();
        managedComponent.performLookup(container);

        System.out.println(managedComponent);
    }
}