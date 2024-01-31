package org.jgprojects.a.persistence.repository;

import java.util.Optional;

import org.jgprojects.a.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
