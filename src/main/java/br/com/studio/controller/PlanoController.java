package br.com.studio.controller;

import br.com.studio.model.Plano;
import br.com.studio.repository.AlunoRepository;
import br.com.studio.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/plano")
public class PlanoController {

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/salvar")
    public ModelAndView salvar(Plano plano){
        LocalDate agora = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = agora.format(formatter);
        planoRepository.save(plano);
        return new ModelAndView("aluno/lista")
                .addObject("data", data)
                .addObject("alunos", alunoRepository.findAllByOrderByNome())
                .addObject("planos", planoRepository.findAll())
                .addObject(new Plano());
    }
}
