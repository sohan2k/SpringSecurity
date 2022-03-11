package io.spring.springSecurity.Repository;

import io.spring.springSecurity.Domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
