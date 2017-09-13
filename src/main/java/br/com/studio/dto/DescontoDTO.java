package br.com.studio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Table(catalog = "studio", name = "desconto")
public class DescontoDTO implements Serializable {


    private BigDecimal valor;
}
