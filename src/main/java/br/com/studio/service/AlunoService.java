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

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Getter @Setter
    public int idade;

    public void cadastro(AlunoDTO dto) {
        Aluno aluno = new Aluno();

        aluno.setAtivo(true);

        aluno.setUsuarioCadastro(dto.getUsuarioCadastro());

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setTelefone(dto.getTelefone());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setObservacao(dto.getObservacao());

        aluno.setPlano(dto.getPlano());
        aluno.setProfessor(dto.getProfessor());
        aluno.setDataInicioAulas(dto.getDataInicioAulas());

        aluno.setDesconto(dto.getDesconto());
        adicionarValorPlano(dto);
        aluno.setValorPlano(dto.getValorPlano());
        aluno.setValorPlanoTotal(dto.getValorPlanoDesconto());
        aluno.setQtdAulasSemanais(dto.getQtdAulasSemanais());

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

        alunoRepository.save(aluno);
    }

    public void alterarCadastro (Aluno aluno, AlunoDTO dto) {
        aluno.setAtivo(dto.getAtivo());

        aluno.setUsuarioAlteracao(dto.getUsuarioAlteracao());

        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setProfissao(dto.getProfissao());
        aluno.setObservacao(dto.getObservacao());
        aluno.setDataInicioAulas(dto.getDataInicioAulas());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setPlano(dto.getPlano());

        aluno.setDesconto(dto.getDesconto());
        adicionarValorPlano(dto);
        aluno.setProfessor(dto.getProfessor());
        aluno.setValorPlano(dto.getValorPlano());
        aluno.setValorPlanoTotal(dto.getValorPlanoDesconto());
        aluno.setQtdAulasSemanais(dto.getQtdAulasSemanais());

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

        alunoRepository.save(aluno);
    }

    public void adicionarValorPlano(AlunoDTO dto) {

        switch (dto.getPlano()){
            case "Bronze":
                dto.setValorPlano(new BigDecimal(50.00));
                dto.setQtdAulasSemanais(1);
                break;

            case "Prata":
                dto.setValorPlano(new BigDecimal(100.00));
                dto.setQtdAulasSemanais(2);
                break;

            case "Ouro":
                dto.setValorPlano(new BigDecimal(150.00));
                dto.setQtdAulasSemanais(3);
                break;

            case "Especial":
                dto.setValorPlano(new BigDecimal(200.00));
                dto.setQtdAulasSemanais(4);
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

    public Map dasboard (List<Aluno> alunos) {
        Double somaPlanos = alunos.stream().mapToDouble(p -> p.getValorPlano().doubleValue()).sum();
        Double somaPlanosDesconto = alunos.stream().mapToDouble(p -> p.getValorPlanoTotal().doubleValue()).sum();
        Integer bronze = Math.toIntExact(alunos.stream().filter(a -> a.getPlano().equals("Bronze")).map(a -> a.getNome()).count());
        Integer prata = Math.toIntExact(alunos.stream().filter(a -> a.getPlano().equals("Prata")).map(a -> a.getNome()).count());
        Integer ouro = Math.toIntExact(alunos.stream().filter(a -> a.getPlano().equals("Ouro")).map(a -> a.getNome()).count());
        Integer especial = Math.toIntExact(alunos.stream().filter(a -> a.getPlano().equals("Especial")).map(a -> a.getNome()).count());
        Integer segunda = Math.toIntExact(alunos.stream().filter(a -> a.getSegunda().equals(true)).map(a -> a.getNome()).count());

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
