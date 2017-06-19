package br.com.studio.model;

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
@Table(catalog = "studio", name = "pagamento")
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @NotEmpty
    private String mes;

    private Integer ano;

    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    private Boolean avulso;

    private String nomeAvulso;
}
