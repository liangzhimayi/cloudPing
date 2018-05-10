package org.bk.producer.controller;


import org.bk.producer.domain.Message;
import org.bk.producer.domain.MessageAcknowledgement;
import org.bk.producer.service.MessageHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PongController {

    private final MessageHandlerService messageHandlerService;

    @Autowired
    public PongController(MessageHandlerService messageHandlerService) {
        this.messageHandlerService = messageHandlerService;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Resource<MessageAcknowledgement> pongMessage() throws InterruptedException {
        try {
            Thread.sleep(1000);
            Message message = new Message();
            message.setId("testId");
            message.setPayload("test payload");
            return new Resource<>(this.messageHandlerService.handleMessage(message));
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/testFeign", method = RequestMethod.GET)
    public String testFeign() throws InterruptedException {
        try {
            Thread.sleep(10000);
            System.out.println("11111111111111111");
            return "yes i am";
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return null;
        }
    }



}
