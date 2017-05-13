package br.com.studio.controller;

import br.com.studio.model.Aluno;
import br.com.studio.model.ResponseRest;
import br.com.studio.repository.AlunoRepository;
import br.com.studio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/aluno")
public class AlunoController {


    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/ativos")
    public ResponseEntity<?> listarAtivos() {

        Boolean ativo = true;
        return ResponseRest.object(
                alunoRepository.findAllByAtivo(ativo)
        );
    }

    @GetMapping("/inativos")
    public ResponseEntity<?> listarInativos() {

        Boolean inativo = false;
        return ResponseRest.object(
                alunoRepository.findAllByAtivo(inativo)
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Aluno dto){
        Aluno aluno = new Aluno();

        aluno.setAtivo(true);

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setProfessor(dto.getProfessor());
        aluno.setDataInicio(dto.getDataInicio());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setDesconto(dto.getDesconto());

        alunoService.adicionarValorPlano(dto);
        aluno.setValorPlano(dto.getValorPlano());
        aluno.setValorPlanoDesconto(dto.getValorPlanoDesconto());

        aluno.setSegunda(dto.getSegunda());
        aluno.setTerca(dto.getTerca());
        aluno.setQuarta(dto.getQuarta());
        aluno.setQuinta(dto.getQuinta());
        aluno.setSexta(dto.getSexta());

        aluno.setSegundaHora(dto.getSegundaHora());
        aluno.setTercaHora(dto.getTercaHora());
        aluno.setQuartaHora(dto.getQuartaHora());
        aluno.setQuintaHora(dto.getQuintaHora());
        aluno.setSextaHora(dto.getSextaHora());

        aluno.setQtdAulasMensais(dto.getQtdAulasMensais());
        alunoRepository.save(aluno);
        return ResponseRest.ok("Aluno salvo com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Aluno aluno, @RequestBody Aluno dto){
        Assert.notNull(aluno, "Aluno inexistente.");

        aluno.setAtivo(dto.getAtivo());

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setProfessor(dto.getProfessor());
        aluno.setDataInicio(dto.getDataInicio());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setDesconto(dto.getDesconto());

        alunoService.adicionarValorPlano(dto);
        aluno.setValorPlano(dto.getValorPlano());
        aluno.setValorPlanoDesconto(dto.getValorPlanoDesconto());

        aluno.setSegunda(dto.getSegunda());
        aluno.setTerca(dto.getTerca());
        aluno.setQuarta(dto.getQuarta());
        aluno.setQuinta(dto.getQuinta());
        aluno.setSexta(dto.getSexta());

        aluno.setSegundaHora(dto.getSegundaHora());
        aluno.setTercaHora(dto.getTercaHora());
        aluno.setQuartaHora(dto.getQuartaHora());
        aluno.setQuintaHora(dto.getQuintaHora());
        aluno.setSextaHora(dto.getSextaHora());

        aluno.setQtdAulasMensais(dto.getQtdAulasMensais());
        alunoRepository.save(aluno);
        return ResponseRest.ok("Aluno atualizado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Aluno aluno){
        Assert.notNull(aluno, "Aluno não encontrado.");
        return ResponseRest.object(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Aluno aluno){
        Assert.notNull(aluno, "Aluno não encontrado.");
        alunoRepository.delete(aluno);
        return ResponseRest.ok("Aluno excluído!");
    }

}
