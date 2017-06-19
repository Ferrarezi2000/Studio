package br.com.studio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Table(catalog = "studio", name = "pagamento")
public class PagamentoDTO implements Serializable {

    private BigDecimal valorPago;
    private Long alunoId;
    private String mes;
    private Boolean avulso;
    private String nomeAvulso;
}
