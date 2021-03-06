package org.bk.producer.service;

import java.util.logging.Level;
import org.bk.producer.domain.Message;
import org.bk.producer.domain.MessageAcknowledgement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageHandlerServiceImpl implements MessageHandlerService {
    private static final Logger logger = LoggerFactory.getLogger(MessageHandlerServiceImpl.class);


    private final String replyMessage;

    @Autowired
    public MessageHandlerServiceImpl(@Value("${reply.message}") String replyMessage) {
        this.replyMessage = replyMessage;
    }

    @Override
    public MessageAcknowledgement handleMessage(Message message) {
        logger.info("About to Acknowledge");
        try {
            double t = Math.random();
            if(t > 0.95) {
                Thread.sleep(Long.parseLong("22000"));
            } else {
                Thread.sleep(((long)Math.random()*10000));
            }
            
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(MessageHandlerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new MessageAcknowledgement(message.getId(), message.getPayload(), this.replyMessage);
    }


}
