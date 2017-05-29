package br.com.studio.dto;

import lombok.Getter;
import lombok.Setter;

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
    private Date dataNascimento;
    private Date dataInicio;
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
    private Integer qtdAulasMensais;
    private String plano;
    private String professor;
    private BigDecimal desconto;
    private BigDecimal valorPlano;
    private BigDecimal valorPlanoDesconto;
    private String usuarioCadastro;
    private String usuarioAlteracao;
    private String observacao;
}
