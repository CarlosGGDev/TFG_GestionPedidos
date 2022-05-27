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

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<List<User>> getUsers() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByNif(String nif) {
        return this.userRepository.findByNif(nif);
    }

    @Override
    public Optional<User> findByName(String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        Optional<User> userOpt = getUser(userId);
        if (userOpt.isPresent()) {
            userRepository.deleteById(userId);
        }
    }

}
