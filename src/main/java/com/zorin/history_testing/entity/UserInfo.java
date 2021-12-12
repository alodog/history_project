package com.zorin.history_testing.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Zorin Sergey
 */

@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя должно быть заполненно!")
    @Size(min = 2, max = 50, message = "Длина имени должна быть от 1 до 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия должна быть заполнена!")
    @Size(min = 2, max = 50, message = "Длина фамилии должна быть от 1 до 50 символов")
    private String surname;

}
