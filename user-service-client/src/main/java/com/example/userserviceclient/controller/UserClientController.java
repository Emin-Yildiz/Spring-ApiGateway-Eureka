package com.example.userserviceclient.controller;

import com.example.userserviceclient.domain.User;
import com.example.userserviceclient.service.UserClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class UserClientController {

    private final UserClientService userClientService;

    public UserClientController(UserClientService userClientService) {
        this.userClientService = userClientService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsersFromUserService(){
        return ResponseEntity.ok(userClientService.getUsersFromUserService());
    }

    @GetMapping("/gateway")
    public ResponseEntity<List<User>> getUsersFromGateway(){
        return ResponseEntity.ok(userClientService.getUsersFromGateway());
    }
}
