package br.com.studio.service;

import br.com.studio.dto.AlunoDTO;
import br.com.studio.dto.ProfessorDTO;
import br.com.studio.model.Aluno;
import br.com.studio.model.Professor;
import br.com.studio.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired private ProfessorRepository professorRepository;

    public void cadastro(ProfessorDTO dto) {

        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setSobrenome(dto.getSobrenome());

        professorRepository.save(professor);
    }

    public void alterarCadastro (Professor professor, ProfessorDTO dto) {
        professor.setNome(dto.getNome());
        professor.setSobrenome(dto.getSobrenome());

        professorRepository.save(professor);
    }
}
