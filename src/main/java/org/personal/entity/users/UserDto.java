package org.personal.entity.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.personal.entity.roles.Role;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;

    private Boolean isActivated;

    private String refreshToken;

    private Set<Role> roles = new HashSet<>();
}
