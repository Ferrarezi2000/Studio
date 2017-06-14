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
        List<Aluno> alunos = alunoRepository.findAll();
        return ResponseRest.list(alunos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> listarAtivos() {
        List<Aluno> alunos = alunoRepository.findAllByAtivo(true);

        Map retorno = MapBuilder.build()
                .add("alunos", alunos);
        return ResponseRest.object(retorno);
    }

    @GetMapping("/inativos")
    public ResponseEntity<?> listarInativos() {

        List<Aluno> alunos = alunoRepository.findAllByAtivo(false);

        Map retorno = MapBuilder.build()
                .add("alunos", alunos);

        return ResponseRest.object(retorno);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody AlunoDTO dto) {
        Aluno aluno = new Aluno();

        aluno.setAtivo(true);

        aluno.setUsuarioCadastro(dto.getUsuarioCadastro());

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setTelefone(dto.getTelefone());
        aluno.setData_nascimento(dto.getDataNascimento());

        aluno.setPlano(dto.getPlano());
        aluno.setProfessor(dto.getProfessor());
        aluno.setData_inicio_aulas(dto.getDataInicio());

        aluno.setDesconto(dto.getDesconto());
        alunoService.adicionarValorPlano(dto);
        aluno.setValorPlano(dto.getValorPlano());
        aluno.setValorPlanoTotal(dto.getValorPlanoDesconto());
        aluno.setQtdAulasSemanais(dto.getQtdAulasMensais());

        aluno.setSegunda(dto.getSegunda());
        aluno.setTerca(dto.getTerca());
        aluno.setQuarta(dto.getQuarta());
        aluno.setQuinta(dto.getQuinta());
        aluno.setSexta(dto.getSexta());

        aluno.setSegunda_hora(dto.getSegundaHora());
        aluno.setTerca_hora(dto.getTercaHora());
        aluno.setQuarta_hora(dto.getQuartaHora());
        aluno.setQuinta_hora(dto.getQuintaHora());
        aluno.setSexta_hora(dto.getSextaHora());

        alunoRepository.save(aluno);
        return ResponseRest.ok("Aluno salvo com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Aluno aluno, @RequestBody AlunoDTO dto) {
        Assert.notNull(aluno, "Aluno inexistente.");

        aluno.setAtivo(dto.getAtivo());

        aluno.setUsuarioAlteracao(dto.getUsuarioAlteracao());

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setObservacao(dto.getObservacao());
        aluno.setData_inicio_aulas(dto.getDataInicio());
        aluno.setData_nascimento(dto.getDataNascimento());
        aluno.setPlano(dto.getPlano());

        aluno.setDesconto(dto.getDesconto());
        alunoService.adicionarValorPlano(dto);
        aluno.setProfessor(dto.getProfessor());
        aluno.setValorPlano(dto.getValorPlano());
        aluno.setValorPlanoTotal(dto.getValorPlanoDesconto());
        aluno.setQtdAulasSemanais(dto.getQtdAulasMensais());

        aluno.setSegunda(dto.getSegunda());
        aluno.setTerca(dto.getTerca());
        aluno.setQuarta(dto.getQuarta());
        aluno.setQuinta(dto.getQuinta());
        aluno.setSexta(dto.getSexta());

        aluno.setSegunda_hora(dto.getSegundaHora());
        aluno.setTerca_hora(dto.getTercaHora());
        aluno.setQuarta_hora(dto.getQuartaHora());
        aluno.setQuinta_hora(dto.getQuintaHora());
        aluno.setSexta_hora(dto.getSextaHora());

        alunoRepository.save(aluno);
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
