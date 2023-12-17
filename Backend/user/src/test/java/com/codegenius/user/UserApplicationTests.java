package com.codegenius.user;

import com.codegenius.user.domain.dto.DadosCadastroCompleto;
import com.codegenius.user.domain.dto.DadosCadastroUser;
import com.codegenius.user.domain.model.UserModel;
import com.codegenius.user.domain.repository.UserRepository;
import com.codegenius.user.domain.service.UserService;
import com.codegenius.user.domain.service.UserServiceImpl;
import com.codegenius.user.infra.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName(value = "Testando o update de usuário com valores validos")
	public void testUpdateUser_Success() {
		UUID userId = UUID.randomUUID();
		DadosCadastroUser userDTO = new DadosCadastroUser("Updated Name", "updated@code.genius", "newP4ssword!");
		DadosCadastroCompleto userComp = new DadosCadastroCompleto(userId, "Updated name", "updated@code-genius", "newP4ssword!", true);

		UserModel existingUser = new UserModel();
		existingUser.setId(userId);
		existingUser.setName("Old Name");
		existingUser.setEmail("old@code.genius");
		existingUser.setPassword("oldP4ssword!");

		when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
		when(userRepository.findByEmailAndActiveTrue(userDTO.getEmail())).thenReturn(Optional.empty());

		DadosCadastroUser updatedUser = userService.updateUser(userId, userDTO, userComp);

		verify(userRepository).save(existingUser);
		assertEquals("Updated Name", updatedUser.getName());
		assertEquals("updated@code.genius", updatedUser.getEmail());
		assertEquals(passwordEncoder.encode("newP4ssword!"), updatedUser.getPassword());
	}

	@Test
	@DisplayName(value = "Testando o update com um email já existente")
	public void testUpdateUserWithEmailExist_Failure() {
		UUID userId = UUID.randomUUID();
		DadosCadastroUser userDTO = new DadosCadastroUser("Updated Name", "old@code.genius", "newP4ssword!");
		DadosCadastroCompleto userComp = new DadosCadastroCompleto(userId, "Updated Name", "old@code.genius", "newP4ssword!", true);

		UUID existingUserId = UUID.randomUUID();
		UserModel existingUser =  new UserModel();
		existingUser.setId(existingUserId);
		existingUser.setName("Old Name");
		existingUser.setEmail("old@code.genius");
		existingUser.setPassword("oldP4ssword!");

		when(userRepository.findByEmailAndActiveTrue(userDTO.getEmail())).thenReturn(Optional.of(existingUser));

		assertThrows(GlobalExceptionHandler.BadRequestException.class, () -> userService.updateUser(userId, userDTO, userComp));

		verify(userRepository, never()).save(any(UserModel.class));
	}

	@Test
	@DisplayName(value = "Testando salvar o usuário com valores validos")
	public void testSaveUser_Success() {
		UUID userId = UUID.randomUUID();
		DadosCadastroUser userDTO = new DadosCadastroUser("New Name", "new@code.genius", "newP4ssword!");
		DadosCadastroCompleto userComp = new DadosCadastroCompleto(userId, "New Name", "new@code.genius", "newP4ssword!", true);

		when(userRepository.findByEmailAndActiveTrue(userDTO.getEmail())).thenReturn(Optional.empty());
		when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");

		UserModel userModel = new UserModel();
		userModel.setName(userDTO.getName());
		userModel.setEmail(userDTO.getEmail());
		userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userModel.setActive(userComp.getActive());

		when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

		DadosCadastroUser savedUser = userService.saveUser(userDTO, userComp);

		verify(userRepository).save(any(UserModel.class));

		assertEquals("New Name", savedUser.getName());
		assertEquals("new@code.genius", savedUser.getEmail());
		assertEquals("encodedPassword", savedUser.getPassword());
	}

	@Test
	@DisplayName(value = "Testando salvar o usuário com um email já existente")
	public void testSaveUserWithEmailExist_Failure() {
		UUID userId = UUID.randomUUID();
		DadosCadastroUser userDTO = new DadosCadastroUser("New Name", "new@code.genius", "newP4ssword!");
		DadosCadastroCompleto userComp = new DadosCadastroCompleto(userId, "New Name", "new@code.genius", "newP4ssword!", true);

		UserModel existingUser = new UserModel();
		existingUser.setId(userId);
		existingUser.setName("Old Name");
		existingUser.setEmail("new@code.genius");
		existingUser.setPassword("oldP4ssword!");
		existingUser.setActive(true);

		when(userRepository.findByEmailAndActiveTrue(userDTO.getEmail())).thenReturn(Optional.of(existingUser));
		when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");

		UserModel userModel = new UserModel();
		userModel.setName(userDTO.getName());
		userModel.setEmail(userDTO.getEmail());
		userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userModel.setActive(userComp.getActive());

		when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

		assertThrows(GlobalExceptionHandler.BadRequestException.class, () -> userService.saveUser(userDTO, userComp));

		verify(userRepository, never()).save(any(UserModel.class));
	}

	@Test
	@DisplayName(value = "Testando desativar um usuário")
	public void testMarkUserAsInactive_Success() {
		UUID userId = UUID.randomUUID();

		UserModel user = new UserModel();
		user.setId(userId);
		user.setActive(true);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		userService.markUserAsInactive(userId);

		assertFalse(user.getActive());

		verify(userRepository).save(user);
	}

	@Test
	@DisplayName(value = "Testando desativar um usuário invalido")
	public void testMarkUserAsInactive_Failure() {
		UUID userId = UUID.randomUUID();

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> userService.markUserAsInactive(userId));

		verify(userRepository, never()).save(any(UserModel.class));
	}

	@Test
	@DisplayName(value = "Testando achar um usuário por id")
	public void testFindById_Success() {
		UUID userId = UUID.randomUUID();

		UserModel user = new UserModel();
		user.setId(userId);
		user.setName("Find");
		user.setEmail("find@code.genius");
		user.setPassword("hashedPassword");
		user.setActive(true);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		DadosCadastroUser result = userService.findById(userId);

		assertNotNull(result);
		assertEquals("Find", result.getName());
		assertEquals("find@code.genius", result.getEmail());
		assertEquals("hashedPassword", result.getPassword());

		verify(userRepository).findById(userId);
	}

	@Test
	@DisplayName(value = "Testando achar um usuário com um id invalido")
	public void testFindByIdNotFound_Failure() {
		UUID userId = UUID.randomUUID();

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> userService.findById(userId));

		verify(userRepository).findById(userId);
	}

	@Test
	@DisplayName("Testando achar um usuário inativo")
	public void testFindByIdAsInactive_Failure() {
		UUID userId = UUID.randomUUID();

		UserModel user = new UserModel();
		user.setId(userId);
		user.setActive(false);

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> userService.findById(userId));

		verify(userRepository).findById(userId);
	}

	@Test
	@DisplayName(value = "Testando achar um usuário pelo seu email")
	public void testFindByEmail_Success() {
		UUID userId = UUID.randomUUID();

		UserModel user = new UserModel();
		user.setId(userId);
		user.setName("Find");
		user.setEmail("find@code.genius");
		user.setPassword("hashedPassword");
		user.setActive(true);

		when(userRepository.findByEmailAndActiveTrue(user.getEmail())).thenReturn(Optional.of(user));

		DadosCadastroCompleto result = userService.findByEmail(user.getEmail());

		assertNotNull(result);
		assertEquals("Find", result.getName());
		assertEquals("find@code.genius", result.getEmail());
		assertEquals("hashedPassword", result.getPassword());

		verify(userRepository).findByEmailAndActiveTrue(result.getEmail());
	}

	@Test
	@DisplayName(value = "Testando achar um usuário com um email inativo")
	public void testFindByEmailAsInactive_Failure() {
		UUID userId = UUID.randomUUID();

		UserModel user = new UserModel();
		user.setId(userId);
		user.setEmail("find@code.genius");
		user.setPassword("hashedPassword");
		user.setActive(false);

		when(userRepository.findByEmailAndActiveTrue(user.getEmail())).thenReturn(Optional.empty());

		assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> userService.findByEmail(user.getEmail()));

		verify(userRepository).findByEmailAndActiveTrue(user.getEmail());
	}
}
