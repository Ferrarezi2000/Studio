package br.com.studio.controller;

import br.com.studio.model.*;
import br.com.studio.repository.EnderecoRepository;
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
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid Endereco endereco, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
//            return index(endereco);
        }
        enderecoRepository.save(endereco);
        Contato contato = new Contato();
        contato.setAluno(endereco.getAluno());
        attributes.addFlashAttribute("mensagem", "Endereço Cadastrado com Sucesso!");
        return new ModelAndView("negociacao/contato")
                .addObject(contato);
    }

    @GetMapping("/detalhe")
    public ModelAndView detalhe(@PathVariable Endereco endereco) {
        ModelAndView mv = new ModelAndView("negociacao/contato")
//                .addObject(endereco)
                .addObject(new Contato());
//                .addObject(new Plano())
//                .addObject(new Historico());
        return mv;
    }
}
