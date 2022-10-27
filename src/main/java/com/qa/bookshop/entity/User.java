package com.qa.bookshop.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 2, max = 50, message = "User name must be between 2 and 50 characters only")
    @Pattern(regexp = "^[A-Za-z ]*", message = "Invalid user name, must contain only alphanumeric")
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters only")
    @Pattern(regexp = "^[A-Za-z ]*", message = "Invalid password, must contain only alphanumeric")
    @Column(name = "password")
    private String password;
}