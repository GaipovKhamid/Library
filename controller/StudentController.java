package Library.controller;

import Library.dto.BookDTO;
import Library.dto.StudentDTO;
import Library.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody StudentDTO dto) {
        try {
            StudentDTO responseDTO = studentService.create(dto);
            ResponseEntity<StudentDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            return response;
        } catch (IllegalArgumentException e) {
            ResponseEntity<String> response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @GetMapping(value = "/all")
    public List<StudentDTO> getAll() {
        return studentService.getStudentLisst();
    }

    @RequestMapping("/byid/{id}")
    public StudentDTO getById(@PathVariable("id") String id) {
        return studentService.getById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody StudentDTO dto) {
        try {
            boolean result = studentService.update(id, dto);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id) {
        return studentService.delete(id);
    }
}
