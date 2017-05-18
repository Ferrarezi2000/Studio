package br.com.studio.service;

import br.com.studio.dto.AlunoDTO;
import br.com.studio.model.Aluno;
import br.com.studio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public void adicionarValorPlano(AlunoDTO dto) {
        if (dto.getPlano().equals("Bronze")) {
            dto.setValorPlano(new BigDecimal(50.00));
            dto.setQtdAulasMensais(1);
        }
        if (dto.getPlano().equals("Prata")) {
            dto.setValorPlano(new BigDecimal(100.00));
            dto.setQtdAulasMensais(2);
        }
        if (dto.getPlano().equals("Ouro")) {
            dto.setValorPlano(new BigDecimal(150.00));
            dto.setQtdAulasMensais(3);
        }
        if (dto.getPlano().equals("Especial")) {
            dto.setValorPlano(new BigDecimal(200.00));
            dto.setQtdAulasMensais(4);
        }
        adicionarValorPlanoDesconto(dto);
    }

    public void adicionarValorPlanoDesconto(AlunoDTO dto) {
        BigDecimal desconto = dto.getValorPlano().multiply(dto.getDesconto()).divide(new BigDecimal(100));
        BigDecimal total = dto.getValorPlano().subtract(desconto);
        dto.setValorPlanoDesconto(total);
    }

    public Double valorTotalPlano(List alunos) {
        Stream<Aluno> streamPessoas = alunos.parallelStream();
        Double somaIdade = streamPessoas.mapToDouble(p -> p.getValorPlano().doubleValue()).sum();
        return somaIdade;
    }

    public Double valorTotalPlanoDesconto(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        Double somaIdade = streamAlunos.mapToDouble(p -> p.getValorPlanoDesconto().doubleValue()).sum();
        return somaIdade;
    }

    public long teste(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        long testendo = streamAlunos.filter(a -> a.getPlano().equals("Ouro")).map(a -> a.getNome()).count();
        return testendo;
    }
}
