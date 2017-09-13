package br.com.studio.service;

import br.com.studio.dto.PlanoDTO;
import br.com.studio.dto.ProfessorDTO;
import br.com.studio.model.Plano;
import br.com.studio.model.Professor;
import br.com.studio.repository.PlanoRepository;
import br.com.studio.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PlanoService {

    @Autowired private PlanoRepository planoRepository;

    public void cadastro(PlanoDTO dto) {

        Plano plano = new Plano();
        plano.setNome(dto.getNome());
        plano.setValor(dto.getValor());
        plano.setQtdAulasSemanais(dto.getQtdAulasSemanais());

        planoRepository.save(plano);
    }

    public void alterarCadastro (Plano plano, PlanoDTO dto) {

        plano.setNome(dto.getNome());
        plano.setValor(dto.getValor());
        plano.setQtdAulasSemanais(dto.getQtdAulasSemanais());

        planoRepository.save(plano);
    }

//    private void adicionarValorPlano(Plano dto) {
//
//        switch (dto.getNome()){
//            case "Bronze":
//                dto.setValor(new BigDecimal(50.00));
//                dto.setQtdAulasSemanais(1);
//                break;
//
//            case "Prata":
//                dto.setValor(new BigDecimal(100.00));
//                dto.setQtdAulasSemanais(2);
//                break;
//
//            case "Ouro":
//                dto.setValor(new BigDecimal(150.00));
//                dto.setQtdAulasSemanais(3);
//                break;
//
//            case "Especial":
//                dto.setValor(new BigDecimal(200.00));
//                dto.setQtdAulasSemanais(4);
//                break;
//        }
//        adicionarValorPlanoDesconto(dto);
//    }
}
