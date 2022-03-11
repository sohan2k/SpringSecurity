package io.spring.springSecurity.Repository.Security;

import io.spring.springSecurity.Domain.Security.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<UserSecurity,Integer> {
    Optional<UserSecurity>findByUsername(String username);
}
