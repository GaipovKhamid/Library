package Library.controller;

import Library.dto.StudentBookDTO;
import Library.service.StudentBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/student-book")
@RestController
public class StudentBookController {
    @Autowired
    private StudentBookService studentBookService;

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody StudentBookDTO studentBookDTO) {
        StudentBookDTO result = studentBookService.create(studentBookDTO);
        return ResponseEntity.ok().body(result);
    }
}
