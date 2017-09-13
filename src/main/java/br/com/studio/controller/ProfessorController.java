package br.com.studio.controller;

import br.com.studio.dto.ProfessorDTO;
import br.com.studio.model.Professor;
import br.com.studio.model.ResponseRest;
import br.com.studio.repository.ProfessorRepository;
import br.com.studio.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/professor")
public class ProfessorController extends AbstractRestController{


    @Autowired private ProfessorRepository professorRepository;
    @Autowired private ProfessorService professorService;


    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseRest.list(professorRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ProfessorDTO dto) {
        Assert.notNull(dto, "Erro");
        professorService.cadastro(dto);
        return ResponseRest.created("Professor cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Professor professor, @RequestBody ProfessorDTO dto) {
        Assert.notNull(professor, "Professor inexistente.");
        professorService.alterarCadastro(professor, dto);
        return ResponseRest.ok("Professor atualizado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Professor professor) {
        Assert.notNull(professor, "Usuário não encontrado.");
        return ResponseRest.object(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Professor professor) {
        Assert.notNull(professor, "Aluno não encontrado.");
        professorRepository.delete(professor);
        return ResponseRest.ok("Aluno excluído!");
    }

}
