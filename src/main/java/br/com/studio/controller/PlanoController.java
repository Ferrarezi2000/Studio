package br.com.studio.controller;

import br.com.studio.dto.PlanoDTO;
import br.com.studio.dto.ProfessorDTO;
import br.com.studio.model.Plano;
import br.com.studio.model.Professor;
import br.com.studio.model.ResponseRest;
import br.com.studio.repository.PlanoRepository;
import br.com.studio.repository.ProfessorRepository;
import br.com.studio.service.PlanoService;
import br.com.studio.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/plano")
public class PlanoController extends AbstractRestController{


    @Autowired private PlanoRepository planoRepository;
    @Autowired private PlanoService planoService;


    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseRest.list(planoRepository.findAllByOrderByValor());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody PlanoDTO dto) {
        Assert.notNull(dto, "Erro");
        planoService.cadastro(dto);
        return ResponseRest.created("Plano cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Plano plano, @RequestBody PlanoDTO dto) {
        Assert.notNull(plano, "Professor inexistente.");
        planoService.alterarCadastro(plano, dto);
        return ResponseRest.ok("Plano atualizado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Plano plano) {
        Assert.notNull(plano, "Plano não encontrado.");
        return ResponseRest.object(plano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Plano plano) {
        Assert.notNull(plano, "Plano não encontrado.");
        planoRepository.delete(plano);
        return ResponseRest.ok("Plano excluído!");
    }

}
