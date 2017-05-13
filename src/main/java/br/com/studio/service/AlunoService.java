package br.com.studio.service;

import br.com.studio.model.Aluno;
import br.com.studio.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public void adicionarValorPlano(Aluno dto){
        Assert.isNull(dto.getPlano(), "O Plano não pode ser nulo!");
      if (dto.getPlano().equals("Ouro")){
           dto.setValorPlano(new BigDecimal(50.00));
           adicionarValorPlanoDesconto(dto);
       }
    }

    public void adicionarValorPlanoDesconto(Aluno dto){
        BigDecimal desconto = dto.getValorPlano().multiply(dto.getDesconto()).divide(new BigDecimal(100));
        BigDecimal total = dto.getValorPlano().subtract(desconto);
        dto.setValorPlanoDesconto(total);
    }
}
