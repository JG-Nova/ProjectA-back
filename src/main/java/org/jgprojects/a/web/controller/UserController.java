package org.jgprojects.a.web.controller;

import org.jgprojects.a.persistence.entity.UserEntity;
import org.jgprojects.a.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> getMethodName() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/account")
    public ResponseEntity<UserEntity> getAccount(){
        return ResponseEntity.ok(service.getAccount());
    }
    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity entity) {
        return ResponseEntity.ok(service.save(entity));
    }
    
}
