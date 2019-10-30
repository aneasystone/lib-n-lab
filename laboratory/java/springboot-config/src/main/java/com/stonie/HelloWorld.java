package com.stonie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorld {

    @Value("${application.userName}")
    private String userName;

    @Value("${application.appId}")
    private String appId;

    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }

    @RequestMapping("/config")
    public Map<String, String> config() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("appId", this.appId);
        configMap.put("userName", this.userName);
        return configMap;
    }
}
