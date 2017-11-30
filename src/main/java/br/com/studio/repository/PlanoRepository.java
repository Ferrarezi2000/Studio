package br.com.studio.repository;

import br.com.studio.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanoRepository extends JpaRepository<Plano, Long> {

    List<Plano> findAllByOrderByValor();

}
