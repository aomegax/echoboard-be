package dev.aomegax.echoboard.be.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Stores feedback exchanged between users.
 *
 * feedback_id	UUID (PK)	Unique identifier for each feedback entry
 * sender_id	UUID (FK)	Reference to the feedback sender (Users)
 * receiver_id	UUID (FK)	Reference to the feedback receiver (Users)
 * content	TEXT	Feedback message
 * created_at	TIMESTAMP	Date and time of feedback submission
 */

@Getter
@Setter
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq_gen")
    @SequenceGenerator(name = "feedback_seq_gen", sequenceName = "feedback_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = true)
    private EndUser sender;

    @OneToOne
    @JoinColumn(nullable = false)
    private EndUser receiver;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;
}
