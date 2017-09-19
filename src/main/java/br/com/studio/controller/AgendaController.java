package br.com.studio.controller;

import br.com.studio.model.ResponseRest;
import br.com.studio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/agenda")
public class AgendaController extends AbstractRestController {

    @Autowired private AlunoRepository alunoRepository;

    @GetMapping("/segunda")
    public ResponseEntity<?> segunda(){
        return ResponseRest.list(alunoRepository.findAllBySegundaAndAtivo(true,true));
    }

    @GetMapping("/terca")
    public ResponseEntity<?> terca(){
        return ResponseRest.list(alunoRepository.findAllByTercaAndAtivo(true,true));
    }

    @GetMapping("/quarta")
    public ResponseEntity<?> quarta(){
        return ResponseRest.list(alunoRepository.findAllByQuartaAndAtivo(true,true));
    }

    @GetMapping("/quinta")
    public ResponseEntity<?> quinta(){
        return ResponseRest.list(alunoRepository.findAllByQuintaAndAtivo(true,true));
    }

    @GetMapping("/sexta")
    public ResponseEntity<?> sexta(){
        return ResponseRest.list(alunoRepository.findAllBySextaAndAtivo(true,true));
    }
}
