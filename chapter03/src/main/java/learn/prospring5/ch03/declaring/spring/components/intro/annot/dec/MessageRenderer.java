package learn.prospring5.ch03.declaring.spring.components.intro.annot.dec;


public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}