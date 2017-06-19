package br.com.studio.repository;


import br.com.studio.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findAllByMes (String mes);

    List<Pagamento> findAllByOrderByDataPagamento();

}
