package Library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDTO {
    private String id;
    @NotNull(message = "Name required")
    private String name;
    @NotNull(message = "Surname required")
    private String surname;
    private Integer phone;
    private LocalDate createdDate;

}
