package br.com.studio.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(catalog = "studio", name = "plano")
public class Plano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotNull
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
