package com.doehrs.feign;

import com.doehrs.dto.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "secondClient", url = "${second.service.url}")
public interface SecondClient {

    String serviceUrl = "/api/hello";

    @RequestMapping(method = RequestMethod.GET, value = serviceUrl + "/")
    CommonResponse getHello(@RequestHeader("Authorization") String token);

    @RequestMapping(method = RequestMethod.GET, value = serviceUrl + "//{id}")
    CommonResponse getHelloById(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id);

}
