package com.madeira.dto.video;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateVideoRequest {

    @Size(min = 3, max = 20, message = "The Name must be from 3 to 20 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$")
    private String name;

    @Size(min = 10, message = "The Description feild must be 10 long")
    private String description;

    @PastOrPresent(message = "The Recorded Date must be Past or Present")
    @NotNull
    private LocalDate recordedDate;

    @URL
    @NotNull(message = "The Link feild cannot be Null")
    private String link;

    @Size(min = 1, message = "There must be atleast 1 tag")
    private List<UUID> tags;

}
