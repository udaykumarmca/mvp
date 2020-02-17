package com.doehrs.feign;

import com.doehrs.auth.FeignClientConfiguration;
import com.doehrs.dto.AuthRequestBody;
import com.doehrs.dto.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "authClient", url = "${first.service.url}", configuration = FeignClientConfiguration.class)
public interface AuthClient {

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AuthResponse getAuth(@RequestBody AuthRequestBody requestBody);

}
