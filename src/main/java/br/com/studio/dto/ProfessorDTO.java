package br.com.studio.dto;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Table(catalog = "studio", name = "professor")
public class ProfessorDTO implements Serializable {

    private String nome;
    private String sobrenome;
}
