package com.lucasangelo.todosimple.moldes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity // criando tababela e definindo que e entidade para o spring boot
@Table(name = user.TABLE_NAME) // criando tabela

public class user {

    public static final String TABLE_NAME = "user";
 // aa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerando codigo
    @Column(name = "id", unique = true) // criando coluna //
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true) // criando coluna
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100) // configuracao
    private String username; // variavel

    @JsonProperty(access = Access.WRITE_ONLY) // nao mandando a senha para o front
    @Column(name = "password", length = 60, nullable = false) // criando coluna
    @NotNull(groups = { CreateUser.class, UpdateUser.class })
    @NotEmpty(groups = { CreateUser.class, UpdateUser.class })
    @Size(groups = { CreateUser.class, UpdateUser.class }, min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "User") // um usuario pode ter varias tarefas
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Task> tasks = new ArrayList<Task>();
}
