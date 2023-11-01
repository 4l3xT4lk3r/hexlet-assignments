package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    // BEGIN
    @Autowired
    private BookMapper mapper;

    @Autowired
    private BookRepository repository;

    public List<BookDTO> getAll(){
        return repository.findAll().stream().map(mapper::map).toList();
    }

    public BookDTO findById(Long id){
        Book book = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book with " + id + " not found!"));
        return mapper.map(book);
    }

    public BookDTO create(BookCreateDTO bookData) throws ConstraintViolationException {
        Book book = mapper.map(bookData);
        repository.save(book);
        return mapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) throws ConstraintViolationException {
        Book book = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book with " + id + " not found!"));
        mapper.update(bookData,book);
        repository.save(book);
        return mapper.map(book);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
    // END
}
