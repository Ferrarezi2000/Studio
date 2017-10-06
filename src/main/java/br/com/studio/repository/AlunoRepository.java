package br.com.studio.repository;

import br.com.studio.model.Aluno;
import br.com.studio.model.Plano;
import br.com.studio.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

     List<Aluno> findAllByOrderByNome();
     List<Aluno> findAllByProfessor(Professor professor);
     List<Aluno> findAllByAtivo(Boolean ativo);
     List<Aluno> findAllBySegundaAndAtivo(Boolean ativoDia, Boolean ativo);
     List<Aluno> findAllByTercaAndAtivo(Boolean ativoDia, Boolean ativo);
     List<Aluno> findAllByQuartaAndAtivo(Boolean ativoDia, Boolean ativo);
     List<Aluno> findAllByQuintaAndAtivo(Boolean ativoDia, Boolean ativo);
     List<Aluno> findAllBySextaAndAtivo(Boolean ativoDia, Boolean ativo);
     List<Aluno> findAllByPlano(Plano plano);
}
