package br.com.studio.controller;

import br.com.studio.dto.DescontoDTO;
import br.com.studio.dto.PlanoDTO;
import br.com.studio.model.Desconto;
import br.com.studio.model.Plano;
import br.com.studio.model.ResponseRest;
import br.com.studio.repository.DescontoRepository;
import br.com.studio.repository.PlanoRepository;
import br.com.studio.service.DescontoService;
import br.com.studio.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/desconto")
public class DescontoController extends AbstractRestController{


    @Autowired private DescontoRepository descontoRepository;
    @Autowired private DescontoService descontoService;


    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseRest.list(descontoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody DescontoDTO dto) {
        Assert.notNull(dto, "Erro");
        descontoService.cadastro(dto);
        return ResponseRest.created("Desconto cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Desconto desconto, @RequestBody DescontoDTO dto) {
        Assert.notNull(desconto, "Desconto inexistente.");
        descontoService.alterarCadastro(desconto, dto);
        return ResponseRest.ok("Desconto atualizado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Desconto desconto) {
        Assert.notNull(desconto, "Desconto não encontrado.");
        return ResponseRest.object(desconto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Desconto desconto) {
        Assert.notNull(desconto, "Desconto não encontrado.");
        descontoRepository.delete(desconto);
        return ResponseRest.ok("Desconto excluído!");
    }

}
