package dev.aomegax.echoboard.be.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Stores the selectable options for multiple-choice questions.
 *
 * option_id	UUID (PK)	Unique identifier for each option
 * question_id	UUID (FK)	Reference to the related question
 * option_text	TEXT	Text of the option
 * order	INT	Order of the option in the list
 */

@Getter
@Setter
@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_seq_gen")
    @SequenceGenerator(name = "option_seq_gen", sequenceName = "option_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne
    private Question question;

    private String optionContent;

    private Integer order;
}
