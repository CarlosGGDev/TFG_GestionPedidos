package dev.gestionpedidos.service;

import dev.gestionpedidos.model.User;
import dev.gestionpedidos.model.UserDetailsAuth;
import dev.gestionpedidos.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// TOREV: el metodo busca por email, pero se llama byUsername porque lo implementa por defecto la interfaz
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return new UserDetailsAuth(user);
	}
}
