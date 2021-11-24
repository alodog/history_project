package com.zorin.history_testing.entity;

import lombok.Data;

import javax.persistence.*;

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
    private String questionText;

    @Column(name = "true_answer")
    private String trueAnswer;

    @Column(name = "wrong_answer0")
    private String wrongAnswer0;

    @Column(name = "wrong_answer1")
    private String wrongAnswer1;

    @Column(name = "wrong_answer2")
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
