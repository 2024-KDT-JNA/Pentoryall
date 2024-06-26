package com.pentoryall.portone.service;


import com.pentoryall.common.dto.CommonResponse;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class PortOneService {

    private final RestTemplate restTemplate;

    private final IamportClient iamportClient;

    @Autowired
    public PortOneService(@Value("${portone.api.key}") String key,
                          @Value("${portone.api.secret}") String secret,
                          RestTemplateBuilder restTemplateBuilder) {
        this.iamportClient = new IamportClient(key, secret);
        this.restTemplate = restTemplateBuilder.build();
    }

    public String issueToken() {
        return iamportClient.getAuth().getResponse().getToken();
    }

    public CommonResponse getAccountHolder(String bankCode, String bankNum) throws URISyntaxException {

        try {
            String accessToken = issueToken();
            String fullUrl = UriComponentsBuilder.fromHttpUrl(IamportClient.API_URL + "/vbanks/holder")
                                                 .queryParam("bank_code", bankCode)
                                                 .queryParam("bank_num", bankNum)
                                                 .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);

            RequestEntity<Object> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(fullUrl));
            IamportResponse iamportResponse = restTemplate.exchange(requestEntity, IamportResponse.class).getBody();

            return new CommonResponse(true, null, iamportResponse.getResponse());

        } catch (HttpClientErrorException e) {
            return new CommonResponse(false, "계좌 검증 실패", null);
        }
    }
}
