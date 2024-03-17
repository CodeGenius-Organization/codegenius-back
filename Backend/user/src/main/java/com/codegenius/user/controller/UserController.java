    package com.codegenius.user.controller;

    import com.codegenius.user.domain.dto.DadosCadastroCompleto;
    import com.codegenius.user.domain.dto.DadosCadastroUser;
    import com.codegenius.user.domain.service.UserService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.media.Schema;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.security.SecurityRequirement;
    import io.swagger.v3.oas.annotations.security.SecurityScheme;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.util.UriComponentsBuilder;

    import java.util.UUID;

    /**
     * Controller class for handling user-related operations.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @RestController
    @RequestMapping("/users")
    @RequiredArgsConstructor
    @CrossOrigin(origins = "*")
    public class UserController {
        private final UserService userService;
        private final HeartController heartController;

        /**
         * Saves user registration data and returns a response with saved user details.
         *
         * @param userDTO       The basic user registration data.
         * @param uriBuilder    The UriComponentsBuilder for building response URI.
         * @param userComp      The complete user registration data.
         * @return ResponseEntity containing saved user data and a created URI.
         *
         * @author hidek
         * @since 2023-08-09
         */
        @PostMapping("/")
        @Operation(summary = "Save user registration data", description = "Endpoint to save user registration data and return the saved user details.")
        @ApiResponse(responseCode = "201", description = "User data saved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosCadastroUser.class)))
        public ResponseEntity<DadosCadastroUser> saveUser(@RequestBody @Valid DadosCadastroUser userDTO, UriComponentsBuilder uriBuilder, DadosCadastroCompleto userComp){
            DadosCadastroUser savedUser = userService.saveUser(userDTO, userComp);

            var uri = uriBuilder.path("/user/{id}").buildAndExpand(userComp.getId()).toUri();
            heartController.createHeart(userComp.getId());
            return ResponseEntity.created(uri).body(savedUser);
        }

        /**
         * Retrieves user details based on the provided user ID.
         *
         * @param id The unique identifier of the user.
         * @return ResponseEntity containing the user's registration data.
         *
         * @author hidek
         * @since 2023-08-09
         */
        @GetMapping("/{id}")
        @SecurityRequirement(name = "Bearer Authentication")
        @Operation(summary = "Retrieve user details by ID", description = "Endpoint to retrieve user details based on the provided user ID.")
        @ApiResponse(responseCode = "200", description = "User details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosCadastroUser.class)))
        public ResponseEntity<DadosCadastroUser> findById(@PathVariable UUID id) {
            DadosCadastroUser user = userService.findById(id);
            return ResponseEntity.status(200).body(user);
        }

        /**
         * Updates user information based on the provided ID and user data.
         *
         * @param id       The unique identifier of the user to update.
         * @param userDTO  The DTO containing the updated user information.
         * @param userComp The complete user registration data.
         * @return ResponseEntity containing the updated user details.
         *
         * @author hidek
         * @since 2023-08-15
         */
        @PutMapping("/{id}")
        @SecurityRequirement(name = "Bearer Authentication")
        @Operation(summary = "Update user information by ID", description = "Endpoint to update user information based on the provided ID and user data.")
        @ApiResponse(responseCode = "200", description = "Updated user details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosCadastroUser.class)))
        public ResponseEntity<DadosCadastroUser> updateUser(
                @PathVariable UUID id,
                @Valid
                @RequestBody DadosCadastroUser userDTO,
                DadosCadastroCompleto userComp) {

            DadosCadastroUser updatedUser = userService.updateUser(id, userDTO, userComp);
            return ResponseEntity.status(200).body(updatedUser);
        }

        /**
         * Marks a user as inactive based on the provided user ID.
         *
         * @param id The unique identifier of the user.
         * @return ResponseEntity with a success message.
         *
         * @author hidek
         * @since 2023-08-15
         */
        @DeleteMapping("/{id}")
        @SecurityRequirement(name = "Bearer Authentication")
        @Operation(summary = "Mark a user as inactive", description = "Endpoint to mark a user as inactive based on the provided user ID.")
        @ApiResponse(responseCode = "204", description = "User marked as inactive")
        public ResponseEntity deleteUser(@PathVariable UUID id) {
            userService.markUserAsInactive(id);
            return ResponseEntity.status(204).build();
        }

        @GetMapping("/info/{email}")
        @SecurityRequirement(name = "Bearer Authentication")
        @Operation(summary = "Retrieve user details by email", description = "Endpoint to retrieve user details based on the provided user email.")
        @ApiResponse(responseCode = "200", description = "User details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosCadastroUser.class)))
        public ResponseEntity<DadosCadastroCompleto> findByEmail(@PathVariable String email) {
            DadosCadastroCompleto user = userService.findByEmail(email);
            return ResponseEntity.status(200).body(user);
        }


    }
