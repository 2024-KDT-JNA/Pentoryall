package com.pentoryall.common.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.IamportResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portone")
public class PortOneApiController {

    private final IamportClient iamportClient;

    public PortOneApiController(@Value("${portone.api.key}") String key,
                                @Value("${portone.api.secret}") String secret) {
        this.iamportClient = new IamportClient(key, secret);
    }

    @PostMapping("/token")
    public IamportResponse<AccessToken> getAccessToken() {
        return iamportClient.getAuth();
    }
}