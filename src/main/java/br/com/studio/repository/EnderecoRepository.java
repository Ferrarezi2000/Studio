package br.com.studio.repository;

import br.com.studio.model.Aluno;
import br.com.studio.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findAllByAluno_id(Long id);
}
