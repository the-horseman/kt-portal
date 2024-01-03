package com.madeira.dto.tag;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTagRequest {

    @NotEmpty(message = "The name feild cannot be empty")
    @Size(min = 3, max = 20, message = "The name must be from 3 to 20 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$")
    private String name;

    @Size(min = 10, message = "The description feild must be atleast 10 long")
    private String description;

    @Size(min = 1, message = "There must be atleast 1 product")
    @NotNull
    private List<UUID> products;

}
