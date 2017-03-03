package br.com.studio.controller;

import br.com.studio.model.*;
import br.com.studio.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid Endereco endereco, Aluno aluno, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
//            return index(endereco);
        }
        enderecoRepository.save(endereco);
        attributes.addFlashAttribute("mensagem", "Endereço Cadastrado com Sucesso!");
        return new ModelAndView("redirect:/aluno/lista")
                .addObject(aluno)
                .addObject(new Endereco())
                .addObject(new Contato())
                .addObject(new Plano())
                .addObject(new Historico());
    }
}
