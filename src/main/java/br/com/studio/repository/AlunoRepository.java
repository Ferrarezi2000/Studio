package br.com.studio.repository;

import br.com.studio.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

     List<Aluno> findAllByOrderByNome();

     List<Aluno> findAllByAtivo(Boolean ativo);
}
