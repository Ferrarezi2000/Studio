package br.com.studio.service;

import br.com.studio.model.Aluno;
import br.com.studio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

//    @Transactional
//    public void teste(Aluno aluno) {
//        LocalDate nascimento = aluno.getDataNascimento();
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        formatador.format(nascimento);
//    }

    public void idade(Aluno aluno) {
        if (aluno.getDataNascimento() != null) {
            LocalDate nascimento = aluno.getDataNascimento();
            Period period = Period.between(nascimento, LocalDate.now());
            Integer idade = period.getYears();
            aluno.setIdade(idade);
            alunoRepository.save(aluno);
        } else {
        }
    }
}
