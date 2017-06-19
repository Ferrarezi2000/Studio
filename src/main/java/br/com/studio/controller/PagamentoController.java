package br.com.studio.controller;

import br.com.studio.dto.PagamentoDTO;
import br.com.studio.model.Aluno;
import br.com.studio.model.Pagamento;
import br.com.studio.model.ResponseRest;
import br.com.studio.repository.AlunoRepository;
import br.com.studio.repository.PagamentoRepository;
import br.com.studio.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pagamento")
public class PagamentoController extends AbstractRestController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<?> todos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAllByOrderByDataPagamento();
        return ResponseRest.list(pagamentos);
    }

    @GetMapping("/mes/{mes}")
    public ResponseEntity<?> pagamentoPorMes(@PathVariable("mes") String mes) {
        Assert.notNull(mes, "Favor informar o Mês desejado");
        List<Pagamento> pagamentos = pagamentoRepository.findAllByMes(mes);
        return ResponseRest.object(pagamentos);
    }

    @PostMapping
    public ResponseEntity<?> pagar(@RequestBody PagamentoDTO dto) {
        pagamentoService.pagamento(dto);
        return ResponseRest.ok("Pagamento realizado com sucesso!");
    }

}
