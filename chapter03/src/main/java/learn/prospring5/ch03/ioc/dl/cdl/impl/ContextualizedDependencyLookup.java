package learn.prospring5.ch03.ioc.dl.cdl.impl;

import learn.prospring5.ch03.ioc.dl.cdl.dec.Container;
import learn.prospring5.ch03.ioc.dl.cdl.dec.ManagedComponent;

public class ContextualizedDependencyLookup implements ManagedComponent {
    private Dependency dependency;

    @Override
    public void performLookup(Container container) {
        this.dependency = (Dependency) container.getDependency("myDependency");
    }
    @Override
    public String toString() {
        return dependency.toString();
    }
}