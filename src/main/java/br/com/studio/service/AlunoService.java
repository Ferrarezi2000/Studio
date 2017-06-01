package br.com.studio.service;

import br.com.studio.dto.AlunoDTO;
import br.com.studio.model.Aluno;
import br.com.studio.model.MapBuilder;
import br.com.studio.repository.AlunoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    public void calculoIdade(Aluno aluno){
        int anoAtual = new Date().getYear();
        int nascimento = aluno.getDataNascimento().getYear();
        idade = anoAtual - nascimento;
    }




    public Map dasboard (List alunos) {
        Stream<Aluno> stream = alunos.parallelStream();
        Double somaPlanos = stream.mapToDouble(p -> p.getValorPlano().doubleValue()).sum();

        Stream<Aluno> stream1 = alunos.parallelStream();
        Double somaPlanosDesconto = stream1.mapToDouble(p -> p.getValorPlanoDesconto().doubleValue()).sum();

        Stream<Aluno> stream2 = alunos.parallelStream();
        Integer bronze = Math.toIntExact(stream2.filter(a -> a.getPlano().equals("Bronze")).map(a -> a.getNome()).count());

        Stream<Aluno> stream3 = alunos.parallelStream();
        Integer prata = Math.toIntExact(stream3.filter(a -> a.getPlano().equals("Prata")).map(a -> a.getNome()).count());

        Stream<Aluno> stream4 = alunos.parallelStream();
        Integer ouro = Math.toIntExact(stream4.filter(a -> a.getPlano().equals("Ouro")).map(a -> a.getNome()).count());

        Stream<Aluno> stream5 = alunos.parallelStream();
        Integer especial = Math.toIntExact(stream5.filter(a -> a.getPlano().equals("Especial")).map(a -> a.getNome()).count());

        Stream<Aluno> stream6 = alunos.parallelStream();
        Integer segunda = (int) stream6.filter(a -> a.getSegunda() == true).map(a -> a.getNome()).count();



        Map retorno = MapBuilder.build()
                .add("somaPlanos", somaPlanos)
                .add("bronze", bronze)
                .add("prata", prata)
                .add("ouro", ouro)
                .add("especial", especial)
                .add("segunda", segunda)
                .add("somaPlanosDesconto", somaPlanosDesconto);

        return retorno;
    }

}
