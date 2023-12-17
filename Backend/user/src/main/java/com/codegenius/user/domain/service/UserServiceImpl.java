package com.codegenius.user.domain.service;

import com.codegenius.user.domain.dto.DadosCadastroCompleto;
import com.codegenius.user.domain.dto.DadosCadastroUser;
import com.codegenius.user.domain.model.UserModel;
import com.codegenius.user.domain.repository.UserRepository;
import com.codegenius.user.infra.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


/**
 * Service implementation for managing user data and registrations.
 *
 * @author hidek
 * @since 2023-08-09
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;
    private PasswordEncoder encoder;

    /**
     * Constructs a new instance of UserServiceImpl.
     *
     * @param userRepository The repository for user data.
     * @param encoder        The password encoder for hashing passwords.
     *
     * @author hidek
     * @since 2023-08-09
     */
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder){
        this.repository = userRepository;
        this.encoder = encoder;
    }

    /**
     * Saves user data and their complete registration details.
     *
     * @param user     The basic user registration data.
     * @param userComp The complete user registration data.
     * @return The saved user's basic registration data.
     * @throws GlobalExceptionHandler.BadRequestException If user data is invalid or user already exists.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public DadosCadastroUser saveUser(DadosCadastroUser user, DadosCadastroCompleto userComp) {
        Optional<UserModel> existingUser = repository.findByEmailAndActiveTrue(user.getEmail());

        if (existingUser.isPresent()) {
            UserModel userFound = existingUser.get();

            if (userFound.getActive().equals(true)) {
                throw new GlobalExceptionHandler.BadRequestException("User with this email already exists.");
            }
        }

        UserModel use = new UserModel(null, user.getName(), user.getEmail(), user.getPassword(), true, null);
        use.setPassword(encoder.encode(use.getPassword()));
        user.setPassword(use.getPassword());

        repository.save(use);

        if (userComp != null && use.getId() != null) {
            userComp.setId(use.getId());
            userComp.setName(use.getName());
            userComp.setEmail(use.getEmail());
            userComp.setPassword(use.getPassword());
            userComp.setActive(use.getActive());
        }

        return user;
    }

    /**
     * Retrieves user information based on the provided user ID.
     *
     * @param id The unique identifier of the user.
     * @return The user's basic registration data associated with the provided ID.
     * @throws GlobalExceptionHandler.NotFoundException If user with the provided ID is not found.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public DadosCadastroUser findById(UUID id) {
        DadosCadastroUser user = new DadosCadastroUser(repository.findById(id)
                .filter(userModel -> Boolean.TRUE.equals(userModel.getActive()))
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("User not found with ID: " + id)));

        return user;
    }

    /**
     * Updates a user's information based on the provided ID and user data.
     *
     * @param id       The unique identifier of the user to update.
     * @param userDTO  The DTO containing the updated user information.
     * @param userComp The complete user registration data.
     * @return The updated user details.
     * @throws GlobalExceptionHandler.BadRequestException if a user with the provided email already exists and is active.
     * @throws GlobalExceptionHandler.NotFoundException  if the user is not found with the provided ID.
     *
     * @author hidek
     * @since 2023-08-15
     */
    @Override
    public DadosCadastroUser updateUser(UUID id, DadosCadastroUser userDTO, DadosCadastroCompleto userComp) {
        Optional<UserModel> existingUser = repository.findByEmailAndActiveTrue(userDTO.getEmail());

        if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
            throw new GlobalExceptionHandler.BadRequestException("User with this email already exists.");
        }

        UserModel userToUpdate = repository.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("User not found with ID: " + id));

        userToUpdate.setName(userDTO.getName());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setPassword(encoder.encode(userDTO.getPassword()));

        repository.save(userToUpdate);

        DadosCadastroUser updatedUser = new DadosCadastroUser(userToUpdate);
        return updatedUser;
    }

    /**
     * Marks a user as inactive based on the provided user ID.
     *
     * @param id The unique identifier of the user to be marked as inactive.
     * @throws GlobalExceptionHandler.NotFoundException if the user with the given ID is not found.
     *
     * @author hidek
     * @since 2023-08-15
     */
    @Override
    public void markUserAsInactive(UUID id) {
        UserModel user = repository.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("User not found with ID: " + id));
        if(user.getActive().equals(false)) {
            throw new GlobalExceptionHandler.NotFoundException("User not found with ID: " + id);
        }
        user.setActive(false);

        repository.save(user);
        System.out.println("\u001B[32mDeleção realizada com sucesso!!!");
    }

    @Override
    public DadosCadastroCompleto findByEmail(String email) {
        UserModel user = repository.findByEmailAndActiveTrue(email)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("User with email not exist"));

        DadosCadastroCompleto userComp = new DadosCadastroCompleto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getActive());

        return userComp;
    }
}
