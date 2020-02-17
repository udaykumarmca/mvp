package com.doehrs.util;

import com.doehrs.dto.AuthResponse;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

@Getter
public class TokenUtil {

    private static TokenUtil tokenUtil;
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Long expiresIn;
    private Date validTill;
    private String scope;
    private String jti;

    private TokenUtil(AuthResponse authResponse) {
        this.accessToken = authResponse.getAccessToken();
        this.tokenType = authResponse.getTokenType();
        this.refreshToken = authResponse.getRefreshToken();
        this.expiresIn = authResponse.getExpiresIn();
        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(authResponse.getCreatedOn() + authResponse.getExpiresIn());
        this.validTill = calendar.getTime();
        this.scope = authResponse.getScope();
        this.jti = authResponse.getJti();
    }

    public static TokenUtil currentInstance() {
        return tokenUtil;
    }

    public static TokenUtil setInstance(AuthResponse authResponse) {
        if (tokenUtil == null)
            tokenUtil = new TokenUtil(authResponse);
        return tokenUtil;
    }

    public String getAccessTokenWithBearer() {
        return "Bearer " + accessToken;
    }

    @Override
    public String toString() {
        return "TokenUtil{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", scope='" + scope + '\'' +
                ", jti='" + jti + '\'' +
                '}';
    }
}
