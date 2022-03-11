package io.spring.springSecurity.Domain.Security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserSecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="user_security_authority",
        joinColumns = {@JoinColumn(name="USER_SECURITY_ID", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name="AUTHORITY_ID",referencedColumnName = "ID")})
    private Set<Authority> authorities;

    @Builder.Default
    private boolean accountNonExpired=true;

    @Builder.Default
    private boolean accountNonLocked=true;

    @Builder.Default
    private boolean credentialsNonExpired=true;

    @Builder.Default
    private  boolean enabled=true;
}
