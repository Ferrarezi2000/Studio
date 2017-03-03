package br.com.studio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(catalog = "studio", name = "endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;

    private String bairro;

    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
