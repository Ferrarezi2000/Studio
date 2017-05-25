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

    @GetMapping("/ativos")
    public ResponseEntity<?> listarAtivos() {
        List<Aluno> alunos = alunoRepository.findAllByAtivo(true);
        int total = alunos.size();

       Double somaPlanos =  alunoService.valorTotalPlano(alunos);
       Double somaPlanosDesconto = alunoService.valorTotalPlanoDesconto(alunos);
       long ouro = alunoService.teste(alunos);

        Map retorno = MapBuilder.build()
                .add("total", total)
                .add("somaPlanos", somaPlanos)
                .add("ouro", ouro)
                .add("somaPlanosDesconto", somaPlanosDesconto)
                .add("alunos", alunos);
        return ResponseRest.object(retorno);
    }

    @GetMapping("/inativos")
    public ResponseEntity<?> listarInativos() {

        List<Aluno> alunos = alunoRepository.findAllByAtivo(false);
        int total = alunos.size();

        Double somaPlanos =  alunoService.valorTotalPlano(alunos);
        Double somaPlanosDesconto = alunoService.valorTotalPlanoDesconto(alunos);

        Map retorno = MapBuilder.build()
                .add("total", total)
                .add("somaPlanos", somaPlanos)
                .add("somaPlanosDesconto", somaPlanosDesconto)
                .add("alunos", alunos);

        return ResponseRest.object(retorno);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody AlunoDTO dto, String nome) {
        Aluno aluno = new Aluno();

        aluno.setAtivo(true);

        aluno.setUsuarioCadastro(dto.getUsuarioCadastro());

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setProfessor(dto.getProfessor());
        aluno.setDataInicio(dto.getDataInicio());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setPlano(dto.getPlano());

        aluno.setDesconto(dto.getDesconto());
        alunoService.adicionarValorPlano(dto);
        aluno.setValorPlano(dto.getValorPlano());
        aluno.setValorPlanoDesconto(dto.getValorPlanoDesconto());
        aluno.setQtdAulasMensais(dto.getQtdAulasMensais());

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

        alunoRepository.save(aluno);
        return ResponseRest.created("Aluno salvo com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Aluno aluno, @RequestBody AlunoDTO dto) {
        Assert.notNull(aluno, "Aluno inexistente.");

        aluno.setAtivo(dto.getAtivo());

        aluno.setUsuarioCadastro(dto.getUsuarioAlteracao());

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setProfessor(dto.getProfessor());
        aluno.setDataInicio(dto.getDataInicio());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setPlano(dto.getPlano());

        aluno.setDesconto(dto.getDesconto());
        alunoService.adicionarValorPlano(dto);
        aluno.setValorPlano(dto.getValorPlano());
        aluno.setValorPlanoDesconto(dto.getValorPlanoDesconto());
        aluno.setQtdAulasMensais(dto.getQtdAulasMensais());

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Aluno aluno) {
        Assert.notNull(aluno, "Aluno não encontrado.");
        alunoRepository.delete(aluno);
        return ResponseRest.ok("Aluno excluído!");
    }

}
