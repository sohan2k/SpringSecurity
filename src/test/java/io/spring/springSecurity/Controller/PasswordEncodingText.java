package io.spring.springSecurity.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    }

    @Test
    void testNoOpPasswordEncoder() {
        PasswordEncoder noOp= NoOpPasswordEncoder.getInstance();
        System.out.println(noOp.encode(PASSWORD));
    }

    @Test
    void testBycrptPasswordEncoder() {
        PasswordEncoder byCrpt=new BCryptPasswordEncoder();
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
}
