package io.spring.springSecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


/* There is no PasswordEncoder mapped for the id "null"
 THIS error is occur because this password is not encoded so it doesnt match
 so we use {noop} OR create a class password encoder
 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize->{ //this authorize object allow any request to the root
            authorize.antMatchers("/","/webjar/**").permitAll(); // this webjar/** means all webjar url
                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{ldap}{SSHA}sMmSuJ/Fh31mrdeF7geOlQFHNCKCoP9sPHecTA==")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{sha256}221eb667e9c018dcf63e4807934dca855a373e3210f110906da56259ec9305f194a62df30ddd9504")
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser("sohan")
                .password("{bcrypt}$2a$10$WUrU0hW0Lw7W/r/GdFcejeLjpXWDgtrxaFBjwZi8IMAosbE8ACenW")
                .roles("CUSTOMER");

        /*here we get all hash code by print it in passwordencoderTest class
        then i pass the hash value with their encoder .
        and run test in indexController
         */
    }


}
