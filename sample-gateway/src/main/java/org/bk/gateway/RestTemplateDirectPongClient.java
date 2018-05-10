package org.bk.gateway;

import com.google.common.collect.Maps;
import org.bk.consumer.domain.Message;
import org.bk.consumer.domain.MessageAcknowledgement;
import org.bk.consumer.feign.PongClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service("restTemplateDirectPongClient")
@RibbonClient(name = "sample-pong")
public class RestTemplateDirectPongClient implements PongClient {

    private final RestOperations restTemplate;

    @Autowired
    public RestTemplateDirectPongClient(@Qualifier("nonLoadbalancedRestTemplate") RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MessageAcknowledgement sendMessage(Message message) {
//        String pongServiceUrl = "http://localhost:8082/message";
        String pongServiceUrl = "http://samplepong/message";
        HttpEntity<Message> requestEntity = new HttpEntity<>(message);
//        this.restTemplate.getForEntity(pongServiceUrl, MessageAcknowledgement.class).getBody();
//        ResponseEntity<MessageAcknowledgement> response =  this.restTemplate.exchange(pongServiceUrl, HttpMethod.GET, requestEntity, MessageAcknowledgement.class, Maps.newHashMap());
//        HttpEntity<Message> requestEntity = new HttpEntity<>(message);
//        ResponseEntity<MessageAcknowledgement> response =  this.restTemplate.exchange("http://samplepong/message", HttpMethod.POST, requestEntity, MessageAcknowledgement.class, Maps.newHashMap());
//        return response.getBody();
        return this.restTemplate.getForEntity(pongServiceUrl, MessageAcknowledgement.class).getBody();
    }

}
