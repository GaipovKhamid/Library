package Library.service;

import Library.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private List<BookDTO> bookList = new LinkedList<>();

    public BookService() {
    }

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public BookDTO getById(String id) {
        for (BookDTO bookDTO : bookList) {
            if (bookDTO.getId().equals(id)) {
                return bookDTO;
            }
        }
        return null;
    }

    public BookDTO create(BookDTO bookDTO) {
        validation(bookDTO);
        bookDTO.setId(UUID.randomUUID().toString());
        bookList.add(bookDTO);
        return bookDTO;
    }

    public boolean update(String id, BookDTO dto) {
        validation(dto);
        BookDTO exist = get(id);
        if (exist == null) {
            throw new IllegalArgumentException("Book not found");
        }

        exist.setTitle(dto.getTitle());
        exist.setAuthor(dto.getAuthor());
        exist.setPublishYear(dto.getPublishYear());
        return false;
    }

    public boolean delete(String id) {
        for (BookDTO bookDTO : bookList) {
            if (bookDTO.getId().equals(id)) {
                bookList.remove(bookDTO);
                return true;
            }
        }
        return false;
    }

    public void validation(BookDTO dto) {
        if (dto.getTitle() == null || dto.getTitle().trim().length() < 2) {
            throw new IllegalArgumentException("Title required");
        }
        if (dto.getAuthor() == null || dto.getAuthor().trim().length() < 2) {
            throw new IllegalArgumentException("Author required");
        }
        if (dto.getPublishYear() == null) {
            throw new IllegalArgumentException("Publish year required");
        }
    }

    public BookDTO get(String id) {
        for (BookDTO exist : bookList) {
            if (exist.getId().equals(id)) {
                return exist;
            }
        }
        return null;
    }

}
