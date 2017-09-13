package br.com.studio.dto;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Table(catalog = "studio", name = "plano")
public class PlanoDTO implements Serializable {

    private String nome;
    private BigDecimal valor;
    private Integer qtdAulasSemanais;
}
