package com.example.AutoCheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AutoCheckController
{
    @GetMapping("/endpoint")
    public String getEndpoint()
    {
        RestTemplate restTemplate = new RestTemplate();

        //Hitting the actuator endpoint of an application running on port 8081
        String uri = "http://localhost:8081/actuator/health/custom";

        //result contains the actuator status of application running on port 8081
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
}


