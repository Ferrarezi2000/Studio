package br.com.studio.service;

import br.com.studio.dto.AlunoDTO;
import br.com.studio.model.*;
import br.com.studio.repository.AlunoRepository;
import br.com.studio.repository.DescontoRepository;
import br.com.studio.repository.PlanoRepository;
import br.com.studio.repository.ProfessorRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AlunoService {

    @Autowired private AlunoRepository alunoRepository;
    @Autowired private PlanoRepository planoRepository;
    @Autowired private ProfessorRepository professorRepository;
    @Autowired private DescontoRepository descontoRepository;

    @Getter @Setter
    public int idade;

    public void cadastro(AlunoDTO dto) {
        Aluno aluno = new Aluno();

        aluno.setAtivo(true);

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setTelefone(dto.getTelefone());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setObservacao(dto.getObservacao());

        Plano plano = planoRepository.findOne(dto.getPlano());
        aluno.setPlano(plano);

        Professor professor = professorRepository.findOne(dto.getProfessor());
        aluno.setProfessor(professor);

        Desconto desconto = descontoRepository.findOne(dto.getDesconto());
        aluno.setDesconto(desconto);

        aluno.setDataInicioAulas(dto.getDataInicioAulas());
        aluno.setSegunda(dto.getSegunda());
        aluno.setTerca(dto.getTerca());
        aluno.setQuarta(dto.getQuarta());
        aluno.setQuinta(dto.getQuinta());
        aluno.setSexta(dto.getSexta());

        aluno.setSegundaHora(dto.getSegundaHora());
        aluno.setTercaHora(dto.getTercaHora());
        aluno.setQuartaHora(dto.getQuartaHora());
        aluno.setQuintaHora(dto.getQuintaHora());
        aluno.setSextaHora(dto.getSextaHora());

        valorPagarMes(aluno, dto);

        alunoRepository.save(aluno);
    }

    public void alterarCadastro (Aluno aluno, AlunoDTO dto) {
        aluno.setAtivo(dto.getAtivo());

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setTelefone(dto.getTelefone());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setObservacao(dto.getObservacao());

        Plano plano = planoRepository.findOne(dto.getPlano());
        aluno.setPlano(plano);

        Professor professor = professorRepository.findOne(dto.getProfessor());
        aluno.setProfessor(professor);

        Desconto desconto = descontoRepository.findOne(dto.getDesconto());
        aluno.setDesconto(desconto);

        aluno.setDataInicioAulas(dto.getDataInicioAulas());
        aluno.setSegunda(dto.getSegunda());
        aluno.setTerca(dto.getTerca());
        aluno.setQuarta(dto.getQuarta());
        aluno.setQuinta(dto.getQuinta());
        aluno.setSexta(dto.getSexta());

        aluno.setSegundaHora(dto.getSegundaHora());
        aluno.setTercaHora(dto.getTercaHora());
        aluno.setQuartaHora(dto.getQuartaHora());
        aluno.setQuintaHora(dto.getQuintaHora());
        aluno.setSextaHora(dto.getSextaHora());

        valorPagarMes(aluno, dto);

        alunoRepository.save(aluno);
    }

    public void inativar (Aluno aluno) {
        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }

    public void ativar (Aluno aluno) {
        aluno.setAtivo(true);
        alunoRepository.save(aluno);
    }

    private void valorPagarMes(Aluno aluno, AlunoDTO dto) {
        Plano plano = planoRepository.findOne(dto.getPlano());
        Desconto desconto = descontoRepository.findOne(dto.getDesconto());

        BigDecimal descontoValor = plano.getValor().multiply(desconto.getValor()).divide(new BigDecimal(100));
        BigDecimal total = plano.getValor().subtract(descontoValor);

        aluno.setValorPagarMes(total);

    }

    public void calculoIdade(Aluno aluno){
        int anoAtual = new Date().getYear();
        int nascimento = aluno.getDataNascimento().getYear();
        idade = anoAtual - nascimento;
    }

    public Map valorTotalAtivos (List<Aluno> alunos) {
        Double soma = alunos.stream().mapToDouble(a -> a.getValorPagarMes().doubleValue()).sum();

        Map retorno = MapBuilder.build()
                .add("lista", alunos)
                .add("soma", soma);

        return retorno;
    }

    public Map dasboard (List<Aluno> alunos) {
        Double somaPlanos = alunos.stream().mapToDouble(a -> a.getValorPagarMes().doubleValue()).sum();
//        Integer bronze = Math.toIntExact(alunos.stream().filter(a -> a.getPlano().equals("Bronze")).map(a -> a.getNome()).count());
//        Integer prata = Math.toIntExact(alunos.stream().filter(a -> a.getPlano().equals("Prata")).map(a -> a.getNome()).count());
//        Integer ouro = Math.toIntExact(alunos.stream().filter(a -> a.getPlano().equals("Ouro")).map(a -> a.getNome()).count());
//        Integer especial = Math.toIntExact(alunos.stream().filter(a -> a.getPlano().equals("Especial")).map(a -> a.getNome()).count());
        Integer segunda = Math.toIntExact(alunos.stream().filter(a -> a.getSegunda().equals(true)).map(a -> a.getNome()).count());
        Integer terca = Math.toIntExact(alunos.stream().filter(a -> a.getTerca().equals(true)).map(a -> a.getNome()).count());
        Integer quarta = Math.toIntExact(alunos.stream().filter(a -> a.getQuarta().equals(true)).map(a -> a.getNome()).count());
        Integer quinta = Math.toIntExact(alunos.stream().filter(a -> a.getQuinta().equals(true)).map(a -> a.getNome()).count());
        Integer sexta = Math.toIntExact(alunos.stream().filter(a -> a.getSexta().equals(true)).map(a -> a.getNome()).count());
        Assert.isTrue(segunda > 0, "teste");

        Map retorno = MapBuilder.build()
                .add("somaPlanos", somaPlanos)
//                .add("bronze", bronze)
//                .add("prata", prata)
//                .add("ouro", ouro)
//                .add("especial", especial)
                .add("segunda", segunda)
                .add("terca", terca)
                .add("quarta", quarta)
                .add("quinta", quinta)
                .add("sexta", sexta);

        return retorno;
    }

}
