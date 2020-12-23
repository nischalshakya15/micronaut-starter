package org.personal.entity.users;

import io.micronaut.http.annotation.Controller;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.personal.base.BaseResource;

@Tag(name = "Users")
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller(UserController.BASE_URL)
public class UserController extends BaseResource<User, UserDto> {

    static final String BASE_URL = "/api/users";

    public UserController(final UserService userService) {
        super(userService);
    }
}
