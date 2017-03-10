package br.com.studio.controller;

import br.com.studio.model.*;
import br.com.studio.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/detalhe")
public class DetalheController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/{id}")
    public ModelAndView index(@PathVariable("id") Aluno aluno) {
        Endereco endereco = new Endereco();
        endereco.setAluno(aluno);
        Contato contato = new Contato();
        contato.setAluno(aluno);
        Plano plano = new Plano();
        plano.setAluno(aluno);
        ModelAndView mv = new ModelAndView("aluno/detalhe")
                .addObject(aluno)
                .addObject(endereco)
                .addObject("enderecos", enderecoRepository.findAllByAluno_id(aluno.getId()))
                .addObject(contato)
                .addObject(plano)
                .addObject(new Historico());
        return mv;
    }
    @PostMapping("/endereco/salvar")
    public ModelAndView salvarEndereco(Endereco endereco){
        enderecoRepository.save(endereco);
        return index(endereco.getAluno());
    }
}
