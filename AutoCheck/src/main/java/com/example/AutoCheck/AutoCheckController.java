package com.example.AutoCheck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@RestController
public class AutoCheckController
{
    @GetMapping("/endpoint")
    public String getEndpoint() throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        //Hitting the actuator endpoint of an application running on port 8081
        String uri = "http://localhost:8081/actuator/health/custom";

        //result contains the actuator status of application running on port 8081
        String payload = restTemplate.getForEntity(uri, String.class).getBody();
        JsonNode jsonNode = objectMapper.readTree(payload);
        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

        return result;
    }
}


