package br.com.studio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Integer telefone;

    private Integer celular;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
}
