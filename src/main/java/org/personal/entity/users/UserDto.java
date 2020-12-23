package org.personal.entity.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.personal.base.BaseDto;
import org.personal.entity.roles.Role;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;

    private Boolean isActivated;

    private String refreshToken;

    private Set<Role> roles = new HashSet<>();
}

