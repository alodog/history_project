package com.zorin.history_testing.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Zorin Sergey
 */

@Entity
@Table(name = "question")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "question_text")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String questionText;

    @Column(name = "true_answer")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String trueAnswer;

    @Column(name = "wrong_answer0")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String wrongAnswer0;

    @Column(name = "wrong_answer1")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String wrongAnswer1;

    @Column(name = "wrong_answer2")
    @NotBlank(message = "Поле не должно быть пустым!")
    private String wrongAnswer2;

    public Question() {
    }

    public Question(String questionText, String trueAnswer, String wrongAnswer0, String wrongAnswer1, String wrongAnswer2) {
        this.questionText = questionText;
        this.trueAnswer = trueAnswer;
        this.wrongAnswer0 = wrongAnswer0;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
    }
}
