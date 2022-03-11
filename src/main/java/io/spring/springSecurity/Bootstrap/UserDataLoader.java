package io.spring.springSecurity.Bootstrap;

import io.spring.springSecurity.Domain.Security.Authority;
import io.spring.springSecurity.Domain.Security.UserSecurity;
import io.spring.springSecurity.Repository.Security.AuthorityRepository;
import io.spring.springSecurity.Repository.Security.UserSecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserSecurityRepository userSecurityRepository;
    private final PasswordEncoder passwordEncoder;


    private void loadSecurityData(){
        Authority admin=authorityRepository.save(Authority.builder().Roles("ADMIN").build());
        Authority user=authorityRepository.save(Authority.builder().Roles("USER").build());
        Authority customer=authorityRepository.save(Authority.builder().Roles("CUSTOMER").build());

        userSecurityRepository.save(UserSecurity.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .authority(admin)
                .build());

        userSecurityRepository.save(UserSecurity.builder()
                .username("user1")
                .password(passwordEncoder.encode("123"))
                .authority(user)
                .build());

        userSecurityRepository.save(UserSecurity.builder()
                .username("sohan")
                .password(passwordEncoder.encode("123"))
                .authority(customer)
                .build());
    }

    @Override
    public void run(String... args) throws Exception {
        if(authorityRepository.count()==0){
            loadSecurityData();
        }

    }
}
