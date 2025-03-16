package dev.aomegax.echoboard.be.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Defines the available questions and their type.
 *
 * question_id	UUID (PK)	Unique identifier for each question
 * content	TEXT	Question content
 * category	VARCHAR(50)	Category (e.g., performance, teamwork)
 * type	ENUM	Type of question: open, multiple_choice, mixed
 * is_active	BOOLEAN	Whether the question is active
 */

@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq_gen")
    @SequenceGenerator(name = "question_seq_gen", sequenceName = "question_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private QuestionCategory category;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private Boolean isActive;
}
