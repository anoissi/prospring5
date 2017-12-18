package learn.prospring5.ch04.making.spring.aware.UsingtheBeanNameAwareInterface;

import org.springframework.beans.factory.BeanNameAware;

public class NamedSinger implements BeanNameAware {
    private String name;

    /**
     * @Implements {@link BeanNameAware#setBeanName(String)}
     */
    @Override
    public void setBeanName(String beanName) {
        this.name = beanName;
    }

    public void sing() {
        System.out.println("Singer " + name + " - sing()");
    }
}