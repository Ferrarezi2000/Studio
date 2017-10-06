package br.com.studio.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(catalog = "studio", name = "aluno")
@EqualsAndHashCode
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    private String profissao;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_plano")
    private Plano plano;

    @ManyToOne
    @JoinColumn(name = "id_desconto")
    private Desconto desconto;

    @NotNull
    @JoinColumn(name = "valor_pagar_mes")
    private BigDecimal valorPagarMes;

//    @Temporal(TemporalType.DATE)
//    private Date dataNascimento;

    private Date dataNascimento;

    private Date dataInicioAulas;

    private String segundaHora;

    private String tercaHora;

    private String quartaHora;

    private String quintaHora;

    private String sextaHora;

    private Boolean segunda;

    private Boolean terca;

    private Boolean quarta;

    private Boolean quinta;

    private Boolean sexta;

    private Boolean ativo;

    private String telefone;

    private String observacao;

}
