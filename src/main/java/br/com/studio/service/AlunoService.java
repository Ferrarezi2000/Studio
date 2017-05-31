package br.com.studio.service;

import br.com.studio.dto.AlunoDTO;
import br.com.studio.model.Aluno;
import br.com.studio.repository.AlunoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Getter @Setter
    public int idade;

    public void adicionarValorPlano(AlunoDTO dto) {

        switch (dto.getPlano()){
            case "Bronze":
                dto.setValorPlano(new BigDecimal(50.00));
                dto.setQtdAulasMensais(1);
                break;

            case "Prata":
                dto.setValorPlano(new BigDecimal(100.00));
                dto.setQtdAulasMensais(2);
                break;

            case "Ouro":
                dto.setValorPlano(new BigDecimal(150.00));
                dto.setQtdAulasMensais(3);
                break;

            case "Especial":
                dto.setValorPlano(new BigDecimal(200.00));
                dto.setQtdAulasMensais(4);
                break;
        }
        adicionarValorPlanoDesconto(dto);
    }

    public void adicionarValorPlanoDesconto(AlunoDTO dto) {
        BigDecimal desconto = dto.getValorPlano().multiply(dto.getDesconto()).divide(new BigDecimal(100));
        BigDecimal total = dto.getValorPlano().subtract(desconto);
        dto.setValorPlanoDesconto(total);
    }

    public Double valorTotalPlano(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        Double somaPlanos = streamAlunos.mapToDouble(p -> p.getValorPlano().doubleValue()).sum();
        return somaPlanos;
    }

    public Double valorTotalPlanoDesconto(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        Double somaPlanos = streamAlunos.mapToDouble(p -> p.getValorPlanoDesconto().doubleValue()).sum();
        return somaPlanos;
    }

    public long planoBronze(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        long bronze = streamAlunos.filter(a -> a.getPlano().equals("Bronze")).map(a -> a.getNome()).count();
        return bronze;
    }

    public long planoPrata(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        long prata = streamAlunos.filter(a -> a.getPlano().equals("Prata")).map(a -> a.getNome()).count();
        return prata;
    }

    public long planoOuro(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        long ouro = streamAlunos.filter(a -> a.getPlano().equals("Ouro")).map(a -> a.getNome()).count();
        return ouro;
    }

    public long planoEspecial(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        long especial = streamAlunos.filter(a -> a.getPlano().equals("Especial")).map(a -> a.getNome()).count();
        return especial;
    }

    public long segunda(List alunos) {
        Stream<Aluno> streamAlunos = alunos.parallelStream();
        long segunda = streamAlunos.filter(a -> a.getPlano().equals(true)).map(a -> a.getNome()).count();
        return segunda;
    }

    public void calculoIdade(Aluno aluno){
        int anoAtual = new Date().getYear();
        int nascimento = aluno.getDataNascimento().getYear();
        idade = anoAtual - nascimento;
    }
}
