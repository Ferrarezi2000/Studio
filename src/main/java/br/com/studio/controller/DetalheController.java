package br.com.studio.controller;

import br.com.studio.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/detalhe/{id}")
public class DetalheController {

    @GetMapping
    public ModelAndView detalhe(@PathVariable("id") Aluno aluno) {
        ModelAndView mv = new ModelAndView("aluno/detalhe")
                .addObject(new Endereco())
                .addObject(new Contato())
                .addObject(new Plano())
                .addObject(new Historico());
        return mv;
    }
}
