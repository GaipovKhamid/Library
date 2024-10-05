package Library.dto;


import Library.enums.StudentBookStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentBookDTO {
    private String id;

    @NotNull(message = "Student id required")
    private String studentId;
    @NotNull(message = "Book id required")
    private String bookId;

    private LocalDateTime createdDate;
    private StudentBookStatus status;
    private LocalDateTime returnedDate;
}
