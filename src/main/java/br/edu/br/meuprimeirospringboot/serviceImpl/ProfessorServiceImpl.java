package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.br.meuprimeirospringboot.entity.Professor;
import br.edu.br.meuprimeirospringboot.repository.ProfessorRepository;
import br.edu.br.meuprimeirospringboot.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<Professor> buscarTodos() {
        return professorRepository.findAll();
    }

    @Override
    public Professor buscarPorId(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        return professor.orElse(null); 
    }

    @Override
    public void excluirPorId(Long id) {
        professorRepository.deleteById(id);
    }

    @Override
    public Professor cadastrar(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor editar(Professor professor) {
        return professorRepository.save(professor);
    }
}

