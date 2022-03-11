package io.spring.springSecurity.Domain.Security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String Roles;

    @ManyToMany(mappedBy = "authorities")
    private Set<UserSecurity> userSecurities;
}
