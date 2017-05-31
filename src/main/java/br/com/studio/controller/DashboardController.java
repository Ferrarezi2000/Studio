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

        Double somaPlanos = alunoService.valorTotalPlano(alunosAtivos);
        Double somaPlanosDesconto = alunoService.valorTotalPlanoDesconto(alunosAtivos);

        Long totalOuro = alunoService.planoOuro(alunosAtivos);
        Long totalBronze = alunoService.planoBronze(alunosAtivos);
        Long totalPrata = alunoService.planoPrata(alunosAtivos);
        Long totalEspecial = alunoService.planoEspecial(alunosAtivos);

        Long totalSegunda = alunoService.segunda(alunosAtivos);


        Map retorno = MapBuilder.build()
                .add("totalOuro", totalOuro)
                .add("totalPrata", totalPrata)
                .add("totalBronze", totalBronze)
                .add("totalEspecial", totalEspecial)
                .add("somaPlanos", somaPlanos)
                .add("totalSegunda", totalSegunda)
                .add("somaPlanosDesconto", somaPlanosDesconto)
                .add("alunosAtivos", alunosAtivos.size())
                .add("alunosInativos", alunosInativos.size());

        return ResponseRest.object(retorno);
    }
}
