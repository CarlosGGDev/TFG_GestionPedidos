package dev.gestionpedidos.service;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User service implementation.
 * The User repository is injected as dependency at controller level.
 * Methods invoke the repository to persist data.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Get all users
     * @return Optional of users list
     */
    @Override
    public Optional<List<User>> getUsers() {
        return Optional.of(this.userRepository.findAll());
    }

    /**
     * Get user by id
     * @param userId User id
     * @return Optional of user
     */
    @Override
    public Optional<User> getUser(int userId) {
        return this.userRepository.findById(userId);
    }

    /**
     * Get user by nif
     * @param nif User nif
     * @return Optional of user
     */
    @Override
    public Optional<User> findByNif(String nif) {
        return this.userRepository.findByNif(nif);
    }

    /**
     * Get user by name
     * @param name User name
     * @return Optional of user
     */
    @Override
    public Optional<User> findByName(String name) {
        return this.userRepository.findByName(name);
    }

    /**
     * Get user by email
     * @param email User email
     * @return Optional of user
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    /**
     * Saves a user
     * @param user User to be saved
     */
    @Override
    public void saveUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    /**
     * Deletes a user
     * @param userId Id of the user to be deleted
     */
    @Override
    public void deleteUser(int userId) {
        Optional<User> userOpt = getUser(userId);
        if (userOpt.isPresent()) {
            this.userRepository.deleteById(userId);
        }
    }

}
