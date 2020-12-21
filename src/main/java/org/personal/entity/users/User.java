package org.personal.entity.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.personal.base.BaseEntity;
import org.personal.entity.roles.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users_auth")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(unique = true, length = 100)
    private String email;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isActivated;

    @Column(length = 200)
    private String refreshToken;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_name", referencedColumnName = "name")
            }
    )
    private Set<Role> roles = new HashSet<>();
}
