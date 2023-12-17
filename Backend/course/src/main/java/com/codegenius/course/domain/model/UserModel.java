package com.codegenius.course.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Entity class representing a user.
 *
 * @author hidek
 * @since 2023-08-09
 */
@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_user", length = 16, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "active", nullable = false)
    private Boolean active;

    /**
     * Returns the authorities granted to the user.
     *
     * @return A list of user's authorities.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return The user's password.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return The user's email.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return Always returns true (account never expires).
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return Always returns true (user is never locked).
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     *
     * @return Always returns true (credentials never expire).
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return Always returns true (user is always enabled).
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
