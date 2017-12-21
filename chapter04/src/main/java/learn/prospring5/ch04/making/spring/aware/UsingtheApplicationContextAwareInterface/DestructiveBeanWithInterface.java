package learn.prospring5.ch04.making.spring.aware.UsingtheApplicationContextAwareInterface;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;
public class DestructiveBeanWithInterface implements ApplicationContextAware{
    private File file;
    private String filePath;
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing  Bean");
        if (filePath == null) {
            throw new IllegalArgumentException(
                    "You must specify the filePath property of " +
                            DestructiveBeanWithInterface.class);
        }
        this.file = new File(filePath);
        this.file.createNewFile();
        System.out.println("File exists: " + file.exists());
    }
    @PreDestroy
    public void destroy() {
        System.out.println("Destroying Bean");
        if(!file.delete()) {
            System.err.println("ERROR: failed to delete file.");
        }
        System.out.println("File exists: " +  file.exists());
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void setApplicationContext(ApplicationContext ctx)
            throws BeansException {
        System.out.print("setting application context");
        if (ctx instanceof GenericXmlApplicationContext) {
            ((GenericXmlApplicationContext)  ctx).registerShutdownHook();
        }
    }
    public static void main(String... args) throws Exception {
        GenericXmlApplicationContext  ctx  =
                new GenericXmlApplicationContext();
        ctx.load("classpath:makingYourBeanSpringAware/destructiveBeanWinterface.xml");
        ctx.refresh();
        ctx.getBean("destructiveBean",
                DestructiveBeanWithInterface.class);
    }
}