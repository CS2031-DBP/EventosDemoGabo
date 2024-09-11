package dbp.week5.asesoriaeventos.User.domain;

import dbp.week5.asesoriaeventos.User.exceptions.UserNotFoundException;
import dbp.week5.asesoriaeventos.User.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    final private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void deleteById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public User update(Integer id, User user) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public User parcialUpdate(Integer id, User user) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (user.getName() != null) {
            userToUpdate.setName(user.getName());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        return userRepository.save(userToUpdate);
    }
}
