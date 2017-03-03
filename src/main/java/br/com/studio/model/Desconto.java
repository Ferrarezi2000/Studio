package br.com.studio.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "desconto")
public class Desconto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é Obrigatório!")
    private String nome;

    @NotEmpty(message = "Valor é Obrigatório!")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
