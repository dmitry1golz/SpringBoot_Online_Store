package com.golzstore.springstore.mappers;

import com.golzstore.springstore.dtos.UserDto;
import com.golzstore.springstore.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
}
