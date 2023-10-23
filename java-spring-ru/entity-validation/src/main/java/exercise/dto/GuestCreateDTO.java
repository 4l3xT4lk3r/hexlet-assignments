package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @Email(message = "Invalid email!")
    private String email;

    @Pattern(regexp = "\\+[0-9]{11,13}", message = "Phone number should be start with plus sign, consist from 11 to 13 digits!")
    private String phoneNumber;

    @Size(min = 4, max = 4, message = "Card should be 4 digits!")
    private String clubCard;

    @FutureOrPresent(message = "Card is expired!")
    private Date cardValidUntil;
}
// END
