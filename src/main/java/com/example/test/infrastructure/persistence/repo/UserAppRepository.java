package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.UserApp;
import com.example.test.infrastructure.persistence.entity.UserAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepository extends JpaRepository<UserAppEntity, Long> {
    Optional<UserAppEntity> findByUsername(String username);
}
