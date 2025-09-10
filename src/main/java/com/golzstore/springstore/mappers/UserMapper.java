package com.golzstore.springstore.mappers;

import com.golzstore.springstore.dtos.RegisterUserRequest;
import com.golzstore.springstore.dtos.UpdateUserRequest;
import com.golzstore.springstore.dtos.UserDto;
import com.golzstore.springstore.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
