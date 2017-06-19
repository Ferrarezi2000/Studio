package br.com.studio.controller;

import br.com.studio.dto.PagamentoDTO;
import br.com.studio.model.Pagamento;
import br.com.studio.model.ResponseRest;
import br.com.studio.repository.PagamentoRepository;
import br.com.studio.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pagamento")
public class PagamentoController extends AbstractRestController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoService pagamentoService;

    private LocalDate ano = LocalDate.now();

    @GetMapping
    public ResponseEntity<?> todos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAllByOrderByDataPagamento();
        return ResponseRest.list(pagamentos);
    }

    @GetMapping("/mes/{mes}")
    public ResponseEntity<?> pagamentoPorMes(@PathVariable("mes") String mes) {
        List<Pagamento> pagamentos = pagamentoRepository.findAllByMes(mes);
        Assert.notEmpty(pagamentos, "Ainda não foram contabilizado pagamentos para o mês informado");
        return ResponseRest.object(pagamentos);
    }

    @PostMapping
    public ResponseEntity<?> pagar(@RequestBody PagamentoDTO dto) {
        Pagamento pagamentoRealizado = pagamentoRepository.findTopByAlunoIdAndMesAndAno(dto.getAlunoId(), dto.getMes(), ano.getYear());
        Assert.isNull(pagamentoRealizado, "Ops... Esse aluno já pagou o mês selecionado" );
        pagamentoService.pagamento(dto);
        return ResponseRest.created("Pagamento realizado com sucesso!");
    }

}
