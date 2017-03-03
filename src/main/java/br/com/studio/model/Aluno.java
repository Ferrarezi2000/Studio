package br.com.studio.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(catalog = "studio", name = "aluno")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é Obrigatório!")
    private String nome;

    @NotEmpty(message = "Sobrenome é Obrigatório!")
    private String sobrenome;

    @NotNull(message = "CPF é Obrigatório!")
    private String cpf;

    private String rg;

    private String profissao;

    private String observacao;

//    @NotEmpty(message = "Data de Nascimento é Obrigatório!")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

//    @NotEmpty(message = "Data de Início das Aulas é Obrigatório!")
    @Column(name = "data_inicio_aula")
    private LocalDate dataInicio;

    private Boolean ativo;

    private String foto;

}
