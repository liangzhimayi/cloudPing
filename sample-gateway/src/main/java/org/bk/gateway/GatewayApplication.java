package org.bk.gateway;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.jni.Thread;
import org.bk.consumer.domain.Message;
import org.bk.consumer.domain.MessageAcknowledgement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients
@RestController
@RequestMapping("/")
@EnableCircuitBreaker
@EnableHystrixDashboard
public class GatewayApplication {

    @Autowired
    PongClient pongClient;

    @Autowired
    RibbonEurekaPongClient ribbonClient;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @RequestMapping("/test")
    public Resource test() {
        Object t = pongClient.test();
        return pongClient.test();
    }

    @RequestMapping("/testFeign")
    public String testFeign() {

        return pongClient.testFeign();
    }

    @RequestMapping("/testTime")
    public void testTime() {

        String sStr[] = {"2017-12-12 13:36:06",
                "2018-01-02 17:53:47",
                "2018-01-03 04:49:40",
                "2018-01-03 05:02:06",
                "2018-01-03 05:02:10",
                "2018-01-03 09:09:55",
                "2018-01-03 11:26:44",
                "2018-01-04 05:01:53",
                "2018-01-04 05:01:54",
                "2018-01-04 05:01:57",
                "2018-01-04 05:01:58",
                "2018-01-04 05:01:59",
                "2018-01-04 05:02:04",
                "2018-01-05 17:06:02",
                "2018-01-05 17:16:10",
                "2018-01-08 22:05:17",
                "2018-01-09 11:09:53",
                "2018-01-10 05:02:17",
                "2018-01-10 05:02:18",
                "2018-01-10 05:02:19",
                "2018-01-10 05:02:21",
                "2018-01-10 05:02:21",
                "2018-01-10 05:02:22",
                "2018-01-10 05:02:22",
                "2018-01-10 05:02:26",
                "2018-01-10 10:00:52",
                "2018-01-11 05:02:14",
                "2018-01-11 05:02:14",
                "2018-01-11 05:02:17",
                "2018-01-11 05:02:19",
                "2018-01-11 05:02:21",
                "2018-01-11 05:02:22",
                "2018-01-11 06:05:29",
                "2018-01-12 05:02:18",
                "2018-01-12 05:02:19",
                "2018-01-12 05:02:20",
                "2018-01-12 17:28:28",
                "2018-01-12 22:26:54",
                "2018-01-13 05:02:12",
                "2018-01-13 05:02:14",
                "2018-01-13 05:02:16",
                "2018-01-13 05:02:18",
                "2018-01-13 05:02:18",
                "2018-01-13 05:02:19",
                "2018-01-13 05:02:20",
                "2018-01-13 05:02:21",
                "2018-01-13 06:52:43",
                "2018-01-14 05:02:14",
                "2018-01-14 14:17:57",
                "2018-01-15 05:02:11",
                "2018-01-15 11:41:49",
                "2018-01-15 18:37:00",
                "2018-01-16 05:02:40",
                "2018-01-16 05:02:42",
                "2018-01-16 05:02:43",
                "2018-01-17 05:02:32",
                "2018-01-17 05:02:33",
                "2018-01-18 05:02:41",
                "2018-01-18 05:02:43",
                "2018-01-19 05:02:25",
                "2018-01-19 08:04:55",
                "2018-01-19 15:37:44",
                "2018-01-19 16:14:31",
                "2018-01-20 06:57:41",
                "2018-01-21 06:15:42",
                "2018-01-21 18:54:32"};
        try {
            for (String s : sStr) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf1.parse(s);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
                s = sdf.format(date);
                System.out.println(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @RequestMapping("/test1")
    public MessageAcknowledgement test1() {
//        Object t = pongClient.test();
//        return pongClient.test();
        Message msg = new Message();
        msg.setId("123");
        System.out.println("1-");

        return ribbonClient.sendMessage(msg);
    }
}
