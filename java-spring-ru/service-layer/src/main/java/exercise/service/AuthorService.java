package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorMapper mapper;

    @Autowired
    private AuthorRepository repository;

    // BEGIN
    public List<AuthorDTO> getAll() {
        return repository.findAll().stream().map(mapper::map).toList();
    }

    public AuthorDTO findById(Long id) {
        Author author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with " + id + " not found!"));
        return mapper.map(author);
    }

    public AuthorDTO create(AuthorCreateDTO authorData) {
        Author author = mapper.map(authorData);
        repository.save(author);
        return mapper.map(author);
    }

    public AuthorDTO update(AuthorUpdateDTO authorData, Long id) {
        Author author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with " + id + " not found!"));
        mapper.update(authorData, author);
        repository.save(author);
        return mapper.map(author);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
