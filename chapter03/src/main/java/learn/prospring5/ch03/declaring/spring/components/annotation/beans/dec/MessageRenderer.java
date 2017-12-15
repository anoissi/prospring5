package learn.prospring5.ch03.declaring.spring.components.annotation.beans.dec;


public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}