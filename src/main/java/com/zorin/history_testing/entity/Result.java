package com.zorin.history_testing.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zorin Sergey
 */
@Data
@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int userId;
}
