package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.br.meuprimeirospringboot.entity.Turma;
import br.edu.br.meuprimeirospringboot.repository.TurmaRepository;
import br.edu.br.meuprimeirospringboot.service.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public List<Turma> buscarTodas() {
        return turmaRepository.findAll();
    }

    @Override
    public Turma buscarPorId(Long id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        return turma.orElse(null);
    }

    @Override
    public void excluirPorId(Long id) {
        turmaRepository.deleteById(id);
    }

    @Override
    public Turma cadastrar(Turma turma) {
        return turmaRepository.save(turma);
    }

    @Override
    public Turma editar(Turma turma) {
        return turmaRepository.save(turma);
    }

    @Override
    public List<Turma> buscarPorDisciplina(Long disciplinaId) {
        return turmaRepository.findTurmasByDisciplinaId(disciplinaId);
    }

    @Override
    public List<Turma> buscarPorSemestre(Long semestreId) {
        return turmaRepository.findTurmasBySemestreId(semestreId);
    }

    @Override
    public Optional<Turma> buscarPorDisciplinaESemestre(Long disciplinaId, Long semestreId) {
        return turmaRepository.findTurmaByDisciplinaAndSemestre(disciplinaId, semestreId);
    }
}
