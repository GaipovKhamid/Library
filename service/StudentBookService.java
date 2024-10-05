package Library.service;

import Library.dto.BookDTO;
import Library.dto.StudentBookDTO;
import Library.dto.StudentDTO;
import Library.enums.StudentBookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentBookService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;


    private List<StudentBookDTO> studentBookList = new ArrayList<>();

    public StudentBookDTO create(StudentBookDTO studentBookDTO) {
        StudentDTO student = studentService.get(studentBookDTO.getStudentId());
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        BookDTO book = bookService.get(studentBookDTO.getBookId());
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        StudentBookDTO studentBook = new StudentBookDTO();
        studentBook.setId(UUID.randomUUID().toString());
        studentBook.setBookId(studentBookDTO.getId());
        studentBook.setStudentId(studentBookDTO.getId());
        studentBook.setStatus(StudentBookStatus.TAKEN);
        studentBook.setCreatedDate(LocalDateTime.now());
        studentBookList.add(studentBook);

        return studentBook;

    }

}
