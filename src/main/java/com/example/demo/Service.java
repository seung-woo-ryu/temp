package com.example.demo;

import com.example.demo.dto.OAuthLoginResponseDto;
import com.example.demo.dto.UserProfileDto;
import com.example.demo.util.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
@Slf4j
public class Service {
    private static final String OAUTH_BASE_URL = ;
    private static final String GET_ACCESS_TOKEN_URL = "/api/v1/user/oauth2/token";
    private static final String GET_USER_INFO = "/api/v1/user/profile";
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;
    @Value("${grant_type}")
    private String grantType;
    @Value("${redirect_uri}")
    private String redirectUri;
    public OAuthLoginResponseDto getAccessToken(String code) {
        return restTemplate
                .postForEntity(
                        OAUTH_BASE_URL + GET_ACCESS_TOKEN_URL,
                        getAccessTokenHttpEntity(code),
                        OAuthLoginResponseDto.class)
                .getBody();
    }

    public UserProfileDto getUserInfo(OAuthLoginResponseDto accessToken) {
        return restTemplate
                .exchange(
                        OAUTH_BASE_URL + GET_USER_INFO,
                        HttpMethod.GET,
                        getUserInfoHttpEntity(accessToken),
                        UserProfileDto.class)
                .getBody();
    }

    private HttpEntity<HttpHeaders> getUserInfoHttpEntity(OAuthLoginResponseDto accessToken) {
        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken.getAccessToken());

        return  new HttpEntity<>(headers);
    }

    private HttpEntity<?> getAccessTokenHttpEntity(String code) {
        // headers
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(Base64Utils.encodeByBase64(clientId + ":" + clientSecret));

        // form parameters
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("client_id", clientId);
        parameters.add("grant_type", grantType);
        parameters.add("redirect_uri", redirectUri);
        parameters.add("code", code);

        return new HttpEntity<>(parameters, headers);
    }
}
