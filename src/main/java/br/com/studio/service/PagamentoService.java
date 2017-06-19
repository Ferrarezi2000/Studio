package br.com.studio.service;

import br.com.studio.dto.PagamentoDTO;
import br.com.studio.model.Aluno;
import br.com.studio.model.Pagamento;
import br.com.studio.repository.AlunoRepository;
import br.com.studio.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Date;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    private Date hoje = new Date();
    private LocalDate ano = LocalDate.now();

    public void pagamento(PagamentoDTO dto) {
        if (dto.getAvulso().equals(true)) {
            Pagamento pagamento = new Pagamento();
            pagamento.setAvulso(true);
            pagamento.setMes(dto.getMes());
            pagamento.setAno(ano.getYear());
            pagamento.setValorPago(dto.getValorPago());
            pagamento.setNomeAvulso(dto.getNomeAvulso());
            pagamento.setDataPagamento(hoje);
            pagamentoRepository.save(pagamento);
        } else {
            Aluno aluno = alunoRepository.findOne(dto.getAlunoId());
            Assert.notNull(aluno, "Aluno não encontrado");
            Pagamento pagamento = new Pagamento();
            pagamento.setAvulso(false);
            pagamento.setMes(dto.getMes());
            pagamento.setAno(ano.getYear());
            pagamento.setAluno(aluno);
            pagamento.setValorPago(dto.getValorPago());
            pagamento.setDataPagamento(hoje);
            pagamentoRepository.save(pagamento);
        }
    }
}
