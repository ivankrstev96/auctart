package com.auctart.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDto {
    @NotNull
    @NotBlank
    public String username;
    @NotNull
    @NotBlank
    public String password;

    @NotBlank
    @NotNull
    @Email
    public String email;
}
