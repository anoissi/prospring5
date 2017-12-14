package learn.prospring5.ch03.declaring.spring.components.annot.dec;


public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}