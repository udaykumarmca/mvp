package com.doehrs.service;

import com.doehrs.dto.CommonResponse;
import com.doehrs.dto.HelloResponse;
import com.doehrs.feign.FirstClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstService {

    @Autowired
    private FirstClient firstClient;

    @Autowired
    private AuthService authService;


    public CommonResponse getHelloFromSecondBy() {
        String accessToken = authService.getAccessToken();
        CommonResponse commonResponse = firstClient.getHello(accessToken);
        if (!commonResponse.isSuccess()) {
            throw new RuntimeException(commonResponse.getError().toString());
        }
        return commonResponse;
    }

    public HelloResponse getHelloById(Integer id) {
        String accessToken = authService.getAccessToken();
        CommonResponse commonResponse = firstClient.getHelloById(accessToken, id);
        if (!commonResponse.isSuccess()) {
            throw new RuntimeException(commonResponse.getError().toString());
        }
        return (HelloResponse) commonResponse.getData();
    }

}
