package org.personal.entity.users;

import org.mapstruct.Mapper;
import org.personal.base.BaseMapper;

@Mapper(componentModel = "jsr330")
public interface UserMapper extends BaseMapper<User, UserDto> {
}
