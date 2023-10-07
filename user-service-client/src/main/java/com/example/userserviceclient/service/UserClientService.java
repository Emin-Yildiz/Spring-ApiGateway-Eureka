package com.example.userserviceclient.service;

import com.example.userserviceclient.domain.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserClientService {

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    public UserClientService(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public List<User> getUsersFromUserService(){
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");

        if (instances != null && !instances.isEmpty()) {
            String url = instances.get(0).getUri().toString();
            url += "/user";
            List<User> result = restTemplate.getForObject(url, List.class);
            return result;
        }else {
            return null;
        }
    }
}
