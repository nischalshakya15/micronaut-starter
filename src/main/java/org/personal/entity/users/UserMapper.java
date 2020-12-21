package org.personal.entity.users;

import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
