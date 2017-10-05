package br.com.studio.controller;

import br.com.studio.dto.AlunoDTO;
import br.com.studio.model.*;
import br.com.studio.repository.AlunoRepository;
import br.com.studio.repository.PlanoRepository;
import br.com.studio.repository.ProfessorRepository;
import br.com.studio.service.AlunoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/aluno")
@Api(description = "Aluno")
public class AlunoController extends AbstractRestController{


    @Autowired private AlunoRepository alunoRepository;
    @Autowired private ProfessorRepository professorRepository;
    @Autowired private AlunoService alunoService;
    @Autowired private PlanoRepository planoRepository;

    @GetMapping
    public ResponseEntity<?> todos(){
        List<Aluno> alunos = alunoRepository.findAllByOrderByNome();
        return ResponseRest.list(alunos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> listarAtivos() {
        List<Aluno> alunos = alunoRepository.findAllByAtivo(true);
        Map retorno = alunoService.valorTotalAtivos(alunos);
        return ResponseRest.object(retorno);
    }

    @GetMapping("/inativos")
    public ResponseEntity<?> listarInativos() {
        List<Aluno> alunos = alunoRepository.findAllByAtivo(false);
        return ResponseRest.list(alunos);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody AlunoDTO dto) {
        Assert.notNull(dto, "Erro");
        alunoService.cadastro(dto);
        return ResponseRest.created("Aluno salvo com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Aluno aluno, @RequestBody AlunoDTO dto) {
        Assert.notNull(aluno, "Aluno inexistente.");
        alunoService.alterarCadastro(aluno, dto);
        return ResponseRest.ok("Aluno atualizado com sucesso!");
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable("id") Aluno aluno) {
        Assert.notNull(aluno, "Aluno inexistente.");
        alunoService.inativar(aluno);
        return ResponseRest.ok("Aluno inativado com sucesso!");
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<?> ATIVAR(@PathVariable("id") Aluno aluno) {
        Assert.notNull(aluno, "Aluno inexistente.");
        alunoService.ativar(aluno);
        return ResponseRest.ok("Aluno ativado com sucesso!");
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Aluno aluno) {
        Assert.notNull(aluno, "Aluno não encontrado.");
        alunoService.calculoIdade(aluno);
        int idade = alunoService.getIdade();
        Map retorno = MapBuilder.build()
                .add("alunoIdade", idade)
                .add("alunoSelecionado", aluno);
        return ResponseRest.object(retorno);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Aluno aluno) {
        Assert.notNull(aluno, "Aluno não encontrado.");
        alunoRepository.delete(aluno);
        return ResponseRest.ok("Aluno excluído!");
    }

    @GetMapping("/find_by_professor/{id}")
    public ResponseEntity<?> pesquisarPorProfessor(@PathVariable("id") Long id) {
        Professor professor = professorRepository.findOne(id);
        Assert.notNull(professor, "Professora não encontrada.");
        return ResponseRest.list(alunoRepository.findAllByProfessor(professor));
    }

    @GetMapping("/find_by_plano/{id}")
    public ResponseEntity<?> pesquisarPorPlano(@PathVariable("id") Long id) {
        Plano plano = planoRepository.findOne(id);
        Assert.notNull(plano, "Plano não encontrado.");
        return ResponseRest.list(alunoRepository.findAllByPlano(plano));
    }

}
