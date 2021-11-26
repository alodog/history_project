package com.zorin.history_testing.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Zorin Sergey
 */
@Data
@Entity
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя должно быть заполненно!")
    @Size(min = 2, max = 30, message = "Длинна имени должна быть от 2 до 30 символов")
    private String username;

    @NotBlank(message = "Пароль должен быть заполнен!")
    @Size(min = 4, max = 30, message = "Длинна пароля должна быть от 4 до 20 символов")
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "Должна быть выбрана хотя бы одна роль!")
    private Set<Role> roles;

    public User() {
        roles= Collections.singleton(Role.USER);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
