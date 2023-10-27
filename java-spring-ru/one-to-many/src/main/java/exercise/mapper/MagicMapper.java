package exercise.mapper;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class MagicMapper {

    @Autowired
    UserRepository userRepository;

    public Long map(User user) {
        return user != null ? user.getId() : null;
    }

    public User map(Long id){
        return userRepository.findById(id).orElse(null);
    }

}
