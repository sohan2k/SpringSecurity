package io.spring.springSecurity.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;


//import static org.springframework.test.util.AssertionErrors.assertTrue;

public class PasswordEncodingText {
    private final String PASSWORD="password";

    @Test
    void hashingExample() {
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
        //here md5DigestAsHex is a hasing algorithm which convert password into hash value

        String SALTED=PASSWORD+"ThisISMySaltedKey";
        System.out.println(DigestUtils.md5DigestAsHex(SALTED.getBytes()));
        /*here i adda asalted value with my password and it provide a different hashcode
        so if attacker know my password they dont know the salted value.so it is now more secure than previous
         */
        String s="guru-spring";
        System.out.println(DigestUtils.md5DigestAsHex(s.getBytes()));


    }

    @Test
    void testNoOpPasswordEncoder() {
        PasswordEncoder noOp= NoOpPasswordEncoder.getInstance();
        System.out.println(noOp.encode(PASSWORD));
    }

    @Test
    void testBycrptPasswordEncoder() {

        //PasswordEncoder byCrpt=new BCryptPasswordEncoder();//here we change the strength og this encoder.

        PasswordEncoder byCrpt=new BCryptPasswordEncoder(12);//its default strength is 10

        System.out.println(byCrpt.encode(PASSWORD));
        System.out.println(byCrpt.encode(PASSWORD));
        //so it always provide new hashcode

    }

    @Test
    void testLdap() {
        PasswordEncoder ldap=new LdapShaPasswordEncoder();
        System.out.println(ldap.encode(PASSWORD));
        System.out.println(ldap.encode(PASSWORD));

        String encoded=ldap.encode(PASSWORD);
        assertTrue(ldap.matches(PASSWORD,encoded));



    }

    @Test
    void testStandardShaPasswordEncoder() {
        PasswordEncoder stdSha=new StandardPasswordEncoder();// it use 256 hashing

        System.out.println(stdSha.encode(PASSWORD));
        System.out.println(stdSha.encode(PASSWORD));

    }

    @Test
    void testdelegatingPasswordEncoder() {
        PasswordEncoder ldap=new LdapShaPasswordEncoder();
        PasswordEncoder stdSha256=new StandardPasswordEncoder();
        PasswordEncoder byCrpt=new BCryptPasswordEncoder();

        System.out.println(ldap.encode("123"));
        System.out.println(stdSha256.encode("123"));
        System.out.println(byCrpt.encode("123"));

    }
}
