package org.jgprojects.a.service;

import org.jgprojects.a.persistence.entity.UserEntity;
import org.jgprojects.a.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found!", username)));
        return User
            .builder()
            .username(user.getId())
            .password(user.getPassword())
            .accountLocked(user.isBlocked())
            .disabled(!user.isActive())
            .build();
    }

}
