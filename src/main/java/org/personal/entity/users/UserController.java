package org.personal.entity.users;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.inject.Inject;
import java.util.List;

@Tag(name = "Users")
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller(UserController.BASE_URL)
public class UserController {

    static final String BASE_URL = "/users";

    @Inject
    private UserService userService;

    @Get
    public HttpResponse<List<User>> findAll() {
        return HttpResponse.ok(userService.findAll());
    }

    @Post
    public HttpResponse<User> create(@Body User user) {
        return HttpResponse.created(userService.save(user));
    }

    @Put("/{id}")
    public HttpResponse<User> update(@PathVariable Long id, @Body User user) {
        if (user.getId() == null || !user.getId().equals(id)) {
            return HttpResponse.badRequest();
        }
        return HttpResponse.ok(userService.update(user));
    }

    @Delete("/{id}")
    public HttpResponse<Void> remove(@PathVariable Long id) {
        User user = userService.findById(id);
        userService.delete(user);
        return HttpResponse.noContent();
    }

    @Get("/{id}")
    public HttpResponse<User> findOne(@PathVariable Long id) {
        return HttpResponse.ok(userService.findById(id));
    }
}
