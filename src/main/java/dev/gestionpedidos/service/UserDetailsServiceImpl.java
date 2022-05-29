package dev.gestionpedidos.service;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.model.UserDetailsAuth;
import dev.gestionpedidos.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User Details service implementation.
 * This class is used by Spring Security to authenticate users.
 * The User repository is injected as dependency at controller level.
 * Methods invoke the repository to persist data.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Get user by email
	 * @param email the username identifying the user whose data is required.
	 * @return UserDetailsAuth object
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOpt = this.userRepository.findByEmail(email);
		if (userOpt.isEmpty()) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return new UserDetailsAuth(userOpt.get());
	}
}
