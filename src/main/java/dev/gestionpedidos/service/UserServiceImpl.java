package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Role;
import dev.gestionpedidos.model.User;
import dev.gestionpedidos.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public User findByNif(String nif) {
        return this.userRepository.findByNif(nif);
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
