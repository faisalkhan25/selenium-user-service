package com.code.userservice.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(@NotBlank(message = "provide the valid name") String name,
                          @NotBlank(message = "Provide the email")
                          @Email(message = "Provide the valid Email") String email) {
}
