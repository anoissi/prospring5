package learn.prospring5.ch03.declaring.spring.components.intro.annotation.beans.dec;


public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}