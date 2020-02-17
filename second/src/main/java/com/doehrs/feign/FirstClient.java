package com.doehrs.feign;

import com.doehrs.dto.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "firstClient", url = "${first.service.url}")
public interface FirstClient {

    String serviceUrl = "/api/hello";

    @RequestMapping(method = RequestMethod.GET, value = serviceUrl + "/")
    CommonResponse getHello(@RequestHeader("Authorization") String token);

    @RequestMapping(method = RequestMethod.GET, value = serviceUrl + "/{id}")
    CommonResponse getHelloById(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id);

}
