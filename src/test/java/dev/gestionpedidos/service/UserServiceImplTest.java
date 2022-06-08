package dev.gestionpedidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.gestionpedidos.model.Role;
import dev.gestionpedidos.model.User;
import dev.gestionpedidos.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * UserService class test cases
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	public static final int USER_ID = 1;
	public static final String NIF = "A00000000";
	public static final String NAME = "name";
	public static final String EMAIL = "email@gmail.com";
	public static final String PHONE = "636123456";
	public static final String ADDRESS = "C/ Test, 4";
	public static final int ZIPCODE = 07001;
	public static final String TOWN = "Palma";
	public static final String PASSWORD = "123";
	public static final Integer ONE = 1;

	@Mock
	private UserRepository userRepository;
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	@InjectMocks
	private UserServiceImpl userService;

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

	List<User> users = List.of(this.user);

	/**
	 * Check that the service returns all users
	 */
	@Test
	void should_return_all_users() {
		when(this.userRepository.findAll()).thenReturn(this.users);

		Optional<List<User>> users = this.userService.getUsers();

		assertEquals(users.get(), this.users);
	}

	/**
	 * Check that the service returns a user
	 */
	@Test
	void should_return_one_user() {
		when(this.userRepository.findById(ONE)).thenReturn(Optional.of(this.user));

		Optional<User> user = this.userService.getUser(ONE);

		assertEquals(user.get(), this.user);
	}

	/**
	 * Check that the service find a user by nif
	 */
	@Test
	void should_find_user_by_nif() {
		when(this.userRepository.findByNif(NIF)).thenReturn(Optional.of(this.user));

		Optional<User> user = this.userService.findByNif(NIF);

		assertEquals(user.get(), this.user);
	}

	/**
	 * Check that the service find a user by name
	 */
	@Test
	void should_find_user_by_name() {
		when(this.userRepository.findByName(NAME)).thenReturn(Optional.of(this.user));

		Optional<User> user = this.userService.findByName(NAME);

		assertEquals(user.get(), this.user);
	}

	/**
	 * Check that the service find a user by email
	 */
	@Test
	void should_find_user_by_email() {
		when(this.userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(this.user));

		Optional<User> user = this.userService.findByEmail(EMAIL);

		assertEquals(user.get(), this.user);
	}

	/**
	 * Check that the service saves a user
	 */
	@Test
	void should_save_one_user() {
		when(this.userRepository.save(this.user)).thenReturn(this.user);

		this.userService.saveUser(this.user);

		verify(this.userRepository, times(1)).save(this.user);
	}

	/**
	 * Check that the service deletes a user
	 */
	@Test
	void should_delete_one_user() {
		when(this.userService.getUser(ONE)).thenReturn(Optional.ofNullable(this.user));
		doNothing().when(this.userRepository).deleteById(ONE);

		this.userService.deleteUser(ONE);

		verify(this.userRepository, times(1)).deleteById(ONE);
	}
}