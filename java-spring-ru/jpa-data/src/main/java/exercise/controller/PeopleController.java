package exercise.controller;

import exercise.repository.PersonRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    // BEGIN
    @GetMapping()
    public List<Person> index() {
        return personRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person){
        personRepository.save(person);
        return person;
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable String id){
        Optional<Person> person = personRepository.findById(Long.parseLong(id));
        if ( person.isPresent()){
            personRepository.delete(person.get());
        }
    }
//    В контроллере для примера уже создан обработчик для маршрута просмотра конкретного человека.
//    Добавьте в приложение обработчики для следующих маршрутов:
//
//    GET /people - Список всех персон
//    POST /people – Создание новой персоны
//    DELETE /people/{id} – Удаление персоны
    // END
}
