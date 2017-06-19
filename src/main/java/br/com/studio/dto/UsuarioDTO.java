package br.com.studio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Table(catalog = "studio", name = "usuario")
public class UsuarioDTO implements Serializable {


    private String nome;
    private String sobrenome;
    private Integer senha;

}
