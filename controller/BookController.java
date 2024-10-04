package Library.controller;

import Library.dto.BookDTO;
import Library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody BookDTO dto) {
        try {
            BookDTO responseDTO = bookService.create(dto);

            ResponseEntity<BookDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            return response;
        } catch (IllegalArgumentException e) {
            ResponseEntity<String> response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @GetMapping(value = "/all")
    public List<BookDTO> getAll() {
        return bookService.getBookList();
    }

    @RequestMapping("/byid/{id}")
    public BookDTO getById(@PathVariable("id") String id) {
        return bookService.getById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody BookDTO dto) {
        try {
            boolean result = bookService.update(id, dto);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id) {
        return bookService.delete(id);
    }
}
