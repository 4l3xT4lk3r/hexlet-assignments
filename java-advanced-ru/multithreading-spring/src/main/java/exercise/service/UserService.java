package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> show(long id) {
        return userRepository.findById(id);
    }

    public Mono<Void> delete(long id) {
        return userRepository.deleteById(id);
    }

    public Mono<User> update(User user, long id) {
        Mono<User> userUpdate = userRepository.findById(id);
        return userUpdate.flatMap( (usr)->{
           usr.setLastName(user.getLastName());
           usr.setFirstName(user.getFirstName());
           usr.setEmail(user.getEmail());
           return userRepository.save(usr);
        });
    }
    // END
}
