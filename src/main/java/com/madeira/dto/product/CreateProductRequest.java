package com.madeira.dto.product;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductRequest {
    
    @NotEmpty(message = "The feild cannot be empty")
    @Size(min = 3, max = 20, message = "The name must be from 3 to 20 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$")
    private String name;

    @NotEmpty(message = "The feild cannot be empty")
    @Size(min = 10)
    private String description;

    @NotNull
    private List<UUID> tags;

}
