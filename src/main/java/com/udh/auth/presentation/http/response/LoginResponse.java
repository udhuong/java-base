package com.udh.auth.presentation.http.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.udh.auth.domain.entity.Token;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginResponse {
    private String tokenType;
    private Long expiresIn;
    private String accessToken;
    private String refreshToken;

    public static LoginResponse format(Token token) {
        LoginResponse response = new LoginResponse();
        response.tokenType = token.getTokenType();
        response.expiresIn = token.getExpiresIn();
        response.accessToken = token.getAccessToken();
        response.refreshToken = token.getRefreshToken();
        return response;
    }
}
