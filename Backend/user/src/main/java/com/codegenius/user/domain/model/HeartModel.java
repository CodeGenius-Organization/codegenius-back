package com.codegenius.user.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity class representing heart-related information for a user stored in the database.
 *
 * @author hidek
 * @since 2023-10-08
 */
@Entity
@Table(name = "user_hearts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeartModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_hearts", length = 16, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "hearts", length = 1, nullable = false)
    private int hearts;
    @Column(name = "last_update", length = 23, nullable = false)
    private LocalDateTime lastUpdate;
    @OneToOne
    @JoinColumn(name = "fk_User", referencedColumnName = "id_user", nullable = false)
    private UserModel fkUser;
}
