package br.com.studio.service;

import br.com.studio.dto.DescontoDTO;
import br.com.studio.dto.PlanoDTO;
import br.com.studio.model.Desconto;
import br.com.studio.model.Plano;
import br.com.studio.repository.DescontoRepository;
import br.com.studio.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescontoService {

    @Autowired private DescontoRepository descontoRepository;

    public void cadastro(DescontoDTO dto) {

        Desconto desconto = new Desconto();
        desconto.setValor(dto.getValor());

        descontoRepository.save(desconto);
    }

    public void alterarCadastro (Desconto desconto, DescontoDTO dto) {

        desconto.setValor(dto.getValor());

        descontoRepository.save(desconto);
    }

}
