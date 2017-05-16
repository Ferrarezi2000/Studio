package br.com.studio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Table(catalog = "studio", name = "aluno")
public class AlunoDTO implements Serializable {


    private String nome;

    private String sobrenome;

    private String profissao;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "data_inicio_aula")
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

}
