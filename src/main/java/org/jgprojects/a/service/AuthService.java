package org.jgprojects.a.service;

import org.jgprojects.a.persistence.entity.UserEntity;
import org.jgprojects.a.persistence.repository.UserRepository;
import org.jgprojects.a.service.dto.LoginDto;
import org.jgprojects.a.service.exception.RestRequestEntityExceptionHandler;
import org.jgprojects.a.web.config.Config;
import org.jgprojects.a.web.config.HashMaker;
import org.jgprojects.a.web.config.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final Config config;

    public AuthService(UserRepository repository, Config config) {
        this.repository = repository;
        this.config = config;
    }
    public String login(LoginDto loginDto){
        try {
            UserEntity user = repository.findByEmail(loginDto.getEmail());
            if(user == null){
                throw new RestRequestEntityExceptionHandler("Error in Auth method cause: Email not found.");
            }
            if(new HashMaker().comparator(user.getPassword(), loginDto.getPassword())){
                throw new RestRequestEntityExceptionHandler("Error in Auth method cause: Wrong password.");
            }
            return new JwtUtil(config).create(user.getId());
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
