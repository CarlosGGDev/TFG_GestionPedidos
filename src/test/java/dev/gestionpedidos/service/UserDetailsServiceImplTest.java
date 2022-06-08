package dev.gestionpedidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import dev.gestionpedidos.model.Role;
import dev.gestionpedidos.model.User;
import dev.gestionpedidos.model.UserDetailsAuth;
import dev.gestionpedidos.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsService class test cases
 */
@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

	public static final int USER_ID = 1;
	public static final String NIF = "A00000000";
	public static final String NAME = "name";
	public static final String EMAIL = "email@gmail.com";
	public static final String PHONE = "636123456";
	public static final String ADDRESS = "C/ Test, 4";
	public static final int ZIPCODE = 07001;
	public static final String TOWN = "Palma";
	public static final String PASSWORD = "123";
	public static final String USER_NAME_EXCEPTION_MESSAGE = "Usuario no encontrado";

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserDetailsServiceImpl userDetailsService;

	User user = User.builder()
		.id(USER_ID)
		.nif(NIF)
		.name(NAME)
		.email(EMAIL)
		.phone(PHONE)
		.address(ADDRESS)
		.zipcode(ZIPCODE)
		.town(TOWN)
		.password(PASSWORD)
		.role(Role.ROLE_USER)
		.build();

	UserDetails userDetails = new UserDetailsAuth(this.user);

	/**
	 * Check that the service throws an exception if not find the user
	 */
	@Test
	void should_throw_UserNameNotFound_exception() {
		when(this.userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

		final UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
																 () -> this.userDetailsService.loadUserByUsername(EMAIL));

		assertEquals(USER_NAME_EXCEPTION_MESSAGE, exception.getMessage());
	}

	/**
	 * Check that the service finds a user by email
	 */
	@Test
	void should_find_user() {
		when(this.userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(this.user));

		UserDetails user = this.userDetailsService.loadUserByUsername(EMAIL);

		assertEquals(user.getUsername(), this.userDetails.getUsername());
	}
}