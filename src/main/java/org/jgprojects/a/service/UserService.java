package org.jgprojects.a.service;

import org.jgprojects.a.persistence.entity.UserEntity;
import org.jgprojects.a.persistence.repository.UserRepository;
import org.jgprojects.a.service.exception.RestRequestEntityExceptionHandler;
import org.jgprojects.a.web.config.HashMaker;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity save(UserEntity user){
        try {
            String password = new HashMaker().hash(user.getPassword());
            user.setPassword(password);
            return repository.save(user);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getLocalizedMessage());
        }
    }
    public UserEntity getAccount(){
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return repository.findById(id).orElseThrow(() -> new RestRequestEntityExceptionHandler("User not found with specified id or Token invalid"));
    }
    public Iterable<UserEntity> findAll(){
        return repository.findAll();
    }
}
