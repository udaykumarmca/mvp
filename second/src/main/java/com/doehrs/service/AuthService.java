package com.doehrs.service;

import com.doehrs.dto.AuthRequestBody;
import com.doehrs.dto.AuthResponse;
import com.doehrs.feign.AuthClient;
import com.doehrs.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private AuthClient authClient;

    @Value("${first.username}")
    private String username;
    @Value("${first.password}")
    private String password;

    private void refreshToken() {
        AuthRequestBody requestBody = AuthRequestBody.builder().grant_type("refresh_token")
                .refresh_token(TokenUtil.currentInstance().getRefreshToken()).build();
        AuthResponse response = authClient.getAuth(requestBody);
        if (response != null) {
            TokenUtil.setInstance(response);
        }
    }

    private void authorize() {
        AuthRequestBody requestBody = AuthRequestBody.builder().grant_type("password")
                .password(password).username(username).build();
        AuthResponse response = authClient.getAuth(requestBody);
        TokenUtil.setInstance(response);
    }

    public String getAccessToken() {
        TokenUtil tokenUtil = TokenUtil.currentInstance();
        if (tokenUtil == null
                || tokenUtil.getRefreshToken() == null
                || tokenUtil.getRefreshToken().isEmpty()) {
            authorize();
        } else if (tokenUtil.getAccessToken() == null
                || tokenUtil.getAccessToken().isEmpty()
                || tokenUtil.getValidTill().after(new Date())){
            refreshToken();
        }
        return TokenUtil.currentInstance().getAccessTokenWithBearer();
    }

}
