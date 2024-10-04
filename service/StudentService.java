package Library.service;

import Library.dto.BookDTO;
import Library.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private List<StudentDTO> studentLisst = new LinkedList<>();

    public StudentService() {
    }

    public List<StudentDTO> getStudentLisst() {
        return studentLisst;
    }

    public StudentDTO getById(String id) {
        for (StudentDTO bookDTO : studentLisst) {
            if (bookDTO.getId().equals(id)) {
                return bookDTO;
            }
        }
        return null;
    }

    public StudentDTO create(StudentDTO bookDTO) {
        validation(bookDTO);
        bookDTO.setId(UUID.randomUUID().toString());
        studentLisst.add(bookDTO);
        return bookDTO;
    }

    public boolean update(String id, StudentDTO dto) {
        validation(dto);
        StudentDTO exist = get(id);
        if (exist == null) {
            throw new IllegalArgumentException("Book not found");
        }

        exist.setName(dto.getName());
        exist.setSurname(dto.getSurname());
        exist.setPhone(dto.getPhone());
        exist.setCreatedDate(dto.getCreatedDate());
        return false;
    }

    public boolean delete(String id) {
        for (StudentDTO bookDTO : studentLisst) {
            if (bookDTO.getId().equals(id)) {
                studentLisst.remove(bookDTO);
                return true;
            }
        }
        return false;
    }

    public void validation(StudentDTO dto) {
        if (dto.getName() == null || dto.getName().trim().length() < 2) {
            throw new IllegalArgumentException("Title required");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().length() < 2) {
            throw new IllegalArgumentException("Author required");
        }
        if (dto.getPhone() == null) {
            throw new IllegalArgumentException("Publish year required");
        }
        if (dto.getCreatedDate() == null) {
            throw new IllegalArgumentException("Created Date required");
        }
    }

    public StudentDTO get(String id) {
        for (StudentDTO exist : studentLisst) {
            if (exist.getId().equals(id)) {
                return exist;
            }
        }
        return null;
    }

}
