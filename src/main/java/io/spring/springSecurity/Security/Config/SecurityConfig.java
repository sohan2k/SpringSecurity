package io.spring.springSecurity.Security.Config;

import io.spring.springSecurity.Security.RestHeaderAuthFilter;
import io.spring.springSecurity.Security.SfgPasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager){
        RestHeaderAuthFilter filter=new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
        http
                .authorizeRequests(authorize->{
            authorize
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/","/api/**").permitAll()
                    .antMatchers(HttpMethod.GET ,"/api/user/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/api/user").permitAll()
                    .mvcMatchers(HttpMethod.GET,"/api/user/{id}").permitAll();
                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
        //H2 -console config
        http.headers().frameOptions().sameOrigin();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{ldap}{SSHA}XS9DftpGn7TPquY/vWwGQGdVMHDHZtnAnfei1Q==")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{sha256}221eb667e9c018dcf63e4807934dca855a373e3210f110906da56259ec9305f194a62df30ddd9504")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{noop}1234")
                .roles("USER");

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
