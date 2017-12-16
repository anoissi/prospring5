package learn.prospring5.ch03.using.method.injection.method;

public class StandardLookupDemoBean implements DemoBean {
    private Singer mySinger;
    public void setMySinger(Singer mySinger) {
        this.mySinger = mySinger;
    }
    @Override
    public Singer getMySinger() {
        return this.mySinger;
    }
    @Override
    public void doSomething() {
        mySinger.sing();
    }
}
//methodInjection/method/app-context.xml