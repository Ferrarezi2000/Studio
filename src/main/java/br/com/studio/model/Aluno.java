package br.com.studio.model;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(catalog = "studio", name = "aluno")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é Obrigatório!")
    private String nome;

    @NotEmpty(message = "Sobrenome é Obrigatório!")
    private String sobrenome;

    @CPF(message = "Erro")
    @NotNull(message = "CPF é Obrigatório!")
    private String cpf;

    private String rg;

    private String profissao;

    private String observacao;

//    @NotEmpty(message = "Data de Nascimento é Obrigatório!")
    @Column(name = "data_nascimento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;

//    @NotEmpty(message = "Data de Início das Aulas é Obrigatório!")
    @Column(name = "data_inicio_aula")
    private LocalDate dataInicio;

    private Boolean ativo;

    private String foto;

    private Boolean virarCliente;

    private Integer idade;
}
