package br.com.studio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(catalog = "studio", name = "contato")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Integer telefone;

    private Integer celular;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
