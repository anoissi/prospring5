package learn.prospring5.ch03.declaring.spring.components.intro.annot.impl;

import learn.prospring5.ch03.declaring.spring.components.intro.annot.dec.MessageProvider;
import learn.prospring5.ch03.declaring.spring.components.intro.annot.dec.MessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("render")
public class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;


    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException(
                    "You must set the property messageProvider of class:"
                            + StandardOutMessageRenderer.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    @Autowired
    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }
    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}