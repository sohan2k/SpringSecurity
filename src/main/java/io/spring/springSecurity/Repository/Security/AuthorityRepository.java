package io.spring.springSecurity.Repository.Security;

import io.spring.springSecurity.Domain.Security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
}
