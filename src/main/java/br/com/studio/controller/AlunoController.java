package br.com.studio.controller;

import br.com.studio.model.*;
import br.com.studio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/aluno")
public class AlunoController {


    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ModelAndView index(Aluno aluno) {
        ModelAndView mv = new ModelAndView("aluno/lista")
                .addObject("alunos", alunoRepository.findAllByOrderByNome());
        return mv;
    }

    @GetMapping("/filtro")
    public ModelAndView filtro(String filtro) {
        ModelAndView mv = new ModelAndView("aluno/lista")
                .addObject(new Aluno())
                .addObject("alunos", alunoRepository.findAllByNomeOrderByNome(filtro));
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView novo(Aluno aluno) {
        ModelAndView mv = new ModelAndView("aluno/cadastro");
        return mv;
    }

    @GetMapping("/salvarApenas")
    public ModelAndView salvarApenas(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return index(aluno);
        }
        alunoRepository.save(aluno);
        attributes.addFlashAttribute("mensagem", "Aluno Cadastrado com Sucesso!");
        return index(new Aluno());
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return index(aluno);
        }
        alunoRepository.save(aluno);
        attributes.addFlashAttribute("mensagem", "Aluno Cadastrado com Sucesso!");
        return detalhe(aluno);
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Aluno aluno) {
        ModelAndView mv = novo(aluno);
        mv.addObject(aluno);
        return mv;
    }

    @GetMapping("/detalhe")
    public ModelAndView detalhe(@PathVariable Aluno aluno) {
        ModelAndView mv = new ModelAndView("aluno/detalhe")
                .addObject(new Endereco())
                .addObject(new Contato())
                .addObject(new Plano())
                .addObject(new Historico());
        return mv;
    }

}
