package com.myapp.myexp.service;

import com.google.gson.Gson;
import com.myapp.myexp.AppConfig;
import com.myapp.myexp.model.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KnowledgeService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppConfig appConfig;

    @Value("${microservices.myapp.api.endpoint}")
    private String myappApiEndpoint;

    @Value("${microservices.myapp.api.allKnowledge.endpoint}")
    private String allKnowledgeEndpoint;

    public Knowledge[] findAllKnowledge(){
        String domain=appConfig.getIp().trim()+":"+appConfig.getPort().trim();
        String url="http://"+domain+myappApiEndpoint+allKnowledgeEndpoint;
        Gson gson = new Gson();
        System.out.println(">"+url+"<");
        ResponseEntity<String> allKnowledge = restTemplate.postForEntity(url, null, String.class);
        Knowledge[] k = gson.fromJson(allKnowledge.getBody(),Knowledge[].class);
        return k;
    }
}
