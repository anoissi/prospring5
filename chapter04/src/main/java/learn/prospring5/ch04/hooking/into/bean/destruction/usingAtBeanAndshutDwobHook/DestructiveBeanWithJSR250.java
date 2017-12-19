package learn.prospring5.ch04.hooking.into.bean.destruction.usingAtBeanAndshutDwobHook;

import java.io.File;
public class DestructiveBeanWithJSR250 {
    private File file;
    private String filePath;

    public void afterPropertiesSet()  throws Exception {
        System.out.println("Initializing  Bean");
        if (filePath == null) {
            throw new IllegalArgumentException(
                    "You must specify the filePath property of " +
                            learn.prospring5.ch04.hooking.into.bean.destruction.UsingtheJSR.DestructiveBeanWithJSR250.class);
        }
        this.file = new File(filePath);
        this.file.createNewFile();
        System.out.println("File exists: " +  file.exists());
    }

    public void destroy() {
        System.out.println("Destroying  Bean");
        if(!file.delete()) {
            System.err.println("ERROR: failed  to delete file.");
        }


        System.out.println("File exists: " +  file.exists());
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}