package io.spring.springSecurity.Bootstrap;

import io.spring.springSecurity.Domain.User;
import io.spring.springSecurity.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    UserRepository userRepository;

    public BootstrapData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User u1=new User();
        u1.setUserName("Sohan");
        u1.setPassword("1234");
        u1.setRoles("admin");

        userRepository.save(u1);

        User u2=new User();
        u2.setUserName("Animesh");
        u2.setPassword("1234");
        u2.setRoles("user");

        userRepository.save(u2);

    }

}
