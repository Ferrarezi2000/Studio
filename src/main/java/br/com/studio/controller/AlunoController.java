package br.com.studio.controller;

import br.com.studio.dto.AlunoDTO;
import br.com.studio.model.Aluno;
import br.com.studio.model.MapBuilder;
import br.com.studio.model.ResponseRest;
import br.com.studio.repository.AlunoRepository;
import br.com.studio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/aluno")
public class AlunoController extends AbstractRestController{


    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<?> todos(){
        List<Aluno> alunos = alunoRepository.findAllByOrderByNome();
        return ResponseRest.list(alunos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> listarAtivos() {
        List<Aluno> alunos = alunoRepository.findAllByAtivo(true);
        return ResponseRest.list(alunos);
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
        return ResponseRest.ok("Aluno salvo com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Aluno aluno, @RequestBody AlunoDTO dto) {
        Assert.notNull(aluno, "Aluno inexistente.");
        alunoService.alterarCadastro(aluno, dto);
        return ResponseRest.ok("Aluno atualizado com sucesso!");
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

}
