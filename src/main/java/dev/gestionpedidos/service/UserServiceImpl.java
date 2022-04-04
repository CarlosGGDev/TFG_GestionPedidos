package dev.gestionpedidos.service;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // CONSTRUCTORS

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CRUD METHODS

    // TODO: que solo pueda acceder el administrador, o si eres el usuario que toca

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> deleteUser(int userId) {
        Optional<User> userOpt = getUser(userId);
        if (userOpt.isPresent()) {
            userRepository.deleteById(userId);
        }
        return userOpt;
    }

}
