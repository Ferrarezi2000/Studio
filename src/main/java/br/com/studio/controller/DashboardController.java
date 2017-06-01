package br.com.studio.controller;

import br.com.studio.model.Aluno;
import br.com.studio.model.MapBuilder;
import br.com.studio.model.ResponseRest;
import br.com.studio.repository.AlunoRepository;
import br.com.studio.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardController extends AbstractRestController{

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<?> listarAlunos() {
        List<Aluno> alunosAtivos = alunoRepository.findAllByAtivo(true);
        List<Aluno> alunosInativos = alunoRepository.findAllByAtivo(false);

        Map dasboard = alunoService.dasboard(alunosAtivos);

        Map retorno = MapBuilder.build()
                .add("alunosInativos", alunosInativos.size())
                .add("alunos", dasboard);

        return ResponseRest.object(retorno);
    }
}
