package com.madeira.dto.employee;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEmployeeRequest {
    
    @NotEmpty(message = "The name feild cannot be empty")
    @Size(min = 3, max = 20, message = "The name must be from 3 to 20 characters.")
    @Pattern(regexp = "^([A-Za-z ]+)$")
    private String name;

    @NotEmpty(message = "The email feild cannot be empty")
    @Email
    private String email;

    @NotEmpty(message = "The password feild cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,20}$")
    private String password;

    @NotEmpty(message = "The role feild cannot be empty")
    @Pattern(regexp = "^(ADMIN|USER)$")
    private String role;

    @NotNull(message = "The personOfContact feild cannot be null")
    private boolean personOfContact;

    @Size(min = 0, message = "There must be atleast 1 product")
    @NotNull
    private List<UUID> products;

}
