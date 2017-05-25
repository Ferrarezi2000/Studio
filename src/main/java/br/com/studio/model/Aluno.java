package br.com.studio.model;

import br.com.studio.dto.AlunoDTO;
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

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "data_inicio_aula")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "segunda_hora")
    private String segundaHora;

    @Column(name = "terca_hora")
    private String tercaHora;

    @Column(name = "quarta_hora")
    private String quartaHora;

    @Column(name = "quinta_hora")
    private String quintaHora;

    @Column(name = "sexta_hora")
    private String sextaHora;

    private Boolean segunda;

    private Boolean terca;

    private Boolean quarta;

    private Boolean quinta;

    private Boolean sexta;

    private Boolean ativo;

    private String telefone;

    @Column(name = "qtda_aulas_mensais")
    private Integer qtdAulasMensais;

    private String plano;

    private String professor;

    private BigDecimal desconto;

    @Column(name = "valor_plano")
    private BigDecimal valorPlano;

    @Column(name = "valor_plano_desconto")
    private BigDecimal valorPlanoDesconto;

    @Column(name = "usuario_cadastro")
    private String usuarioCadastro;

    @Column(name = "usuario_alteracao")
    private String usuarioAlteracao;

}
