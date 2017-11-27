package learn.prospring5.ch03.ioc.injava.dl.cdl.impl;

import learn.prospring5.ch03.ioc.injava.dl.cdl.dec.Container;

public class DefaultContainer implements Container {

    @Override
    public Object getDependency(String key) {
        if("myDependency".equals(key)) {
            return new Dependency();
        }

        throw new RuntimeException("Unknown dependency: " + key);
    }
}