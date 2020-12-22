package org.personal.entity.users;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.inject.Inject;
import java.util.List;

@Tag(name = "Users")
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(UserController.BASE_URL)
public class UserController {

    static final String BASE_URL = "/api/users";

    @Inject
    private UserService userService;

    @Get
    public HttpResponse<List<UserDto>> findAll() {
        return HttpResponse.ok(userService.findAll());
    }

    @Post
    public HttpResponse<UserDto> create(@Body UserDto userDto) {
        return HttpResponse.created(userService.save(userDto));
    }

    @Put("/{id}")
    public HttpResponse<UserDto> update(@PathVariable Long id, @Body UserDto userDto) {
        if (userDto.getId() == null || !userDto.getId().equals(id)) {
            return HttpResponse.badRequest();
        }
        return HttpResponse.ok(userService.update(userDto));
    }

    @Delete("/{id}")
    public HttpResponse<Void> remove(@PathVariable Long id) {
        UserDto userDto = userService.findById(id);
        userService.delete(userDto);
        return HttpResponse.noContent();
    }

    @Get("/{id}")
    public HttpResponse<UserDto> findOne(@PathVariable Long id) {
        return HttpResponse.ok(userService.findById(id));
    }
}
