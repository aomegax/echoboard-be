package dev.aomegax.echoboard.be.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Records user responses to feedback questions.
 *
 * answer_id	UUID (PK)	Unique identifier for each answer
 * feedback_id	UUID (FK)	Reference to the related feedback
 * question_id	UUID (FK)	Reference to the related question
 * option_id	UUID (FK, NULL)	Reference to the selected option (if applicable)
 * comment	TEXT (NULL)	Optional comment for mixed-type questions
 */

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_seq_gen")
    @SequenceGenerator(name = "answer_seq_gen", sequenceName = "answer_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Feedback feedback;

    @OneToOne
    @JoinColumn(nullable = false)
    private Question question;

    @OneToOne
    @JoinColumn(nullable = true)
    private Option option;

    @Column(columnDefinition = "TEXT")
    private String content;
}
