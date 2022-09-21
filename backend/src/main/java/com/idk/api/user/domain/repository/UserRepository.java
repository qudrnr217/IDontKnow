package com.idk.api.user.domain.repository;

import com.idk.api.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByName(String name);

}
