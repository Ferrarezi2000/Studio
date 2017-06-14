package br.com.studio.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(catalog = "studio", name = "aluno")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    @NotEmpty
    private String profissao;

    @Temporal(TemporalType.DATE)
    private Date data_nascimento;

    @Temporal(TemporalType.DATE)
    private Date data_inicio_aulas;

    private String segunda_hora;

    private String terca_hora;

    private String quarta_hora;

    private String quinta_hora;

    private String sexta_hora;

    private Boolean segunda;

    private Boolean terca;

    private Boolean quarta;

    private Boolean quinta;

    private Boolean sexta;

    private Boolean ativo;

    private String telefone;

    private String observacao;

    @Column(name = "qtda_aulas_semanais")
    private Integer qtdAulasSemanais;

    private String plano;

    private String professor;

    private BigDecimal desconto;

    @Column(name = "valor_plano")
    private BigDecimal valorPlano;

    @Column(name = "valor_plano_total")
    private BigDecimal valorPlanoTotal;

    @Column(name = "usuario_cadastro")
    private String usuarioCadastro;

    @Column(name = "usuario_alteracao")
    private String usuarioAlteracao;

}
