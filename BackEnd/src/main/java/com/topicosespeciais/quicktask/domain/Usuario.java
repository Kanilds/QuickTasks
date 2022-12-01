package com.topicosespeciais.quicktask.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "login")
    @NotNull
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @Column(name = "senha")
    @NotNull
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;


}
