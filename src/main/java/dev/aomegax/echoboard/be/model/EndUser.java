package dev.aomegax.echoboard.be.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Stores information about the platform's users.
 *
 * user_id	UUID (PK)	Unique identifier for each user
 * first_name	VARCHAR(50)	User's first name
 * last_name	VARCHAR(50)	User's last name
 * email	VARCHAR(100)	Unique corporate email address
 * role	ENUM	User role: employee, manager, admin
 * hierarchy_level	INT	Hierarchical level (e.g., 1 = junior, 5 = CEO)
 * created_at	TIMESTAMP	Date when the user was created
 */

// TODO wip

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EndUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enduser_seq_gen")
    @SequenceGenerator(name = "enduser_seq_gen", sequenceName = "enduser_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String firstName;
    private String lastName;

    private String googleEmail;
    private String githubEmail;
}



