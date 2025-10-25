package com.golzstore.springstore.auth;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private  String oldPassword;
    private String newPassword;
}
