/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bk.gateway;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author Administrator
 */
@FeignClient("sample-pong")
public interface PongClient {
    @RequestMapping(method = RequestMethod.GET, value = "/message")
    public Resource test();

    @RequestMapping(method = RequestMethod.GET, value = "/testFeign")
    public String testFeign();
}
