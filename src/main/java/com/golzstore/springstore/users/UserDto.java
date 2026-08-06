package com.golzstore.springstore.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
