package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.br.meuprimeirospringboot.entity.Aluno;
import br.edu.br.meuprimeirospringboot.entity.Turma;
import br.edu.br.meuprimeirospringboot.repository.AlunoRepository;
import br.edu.br.meuprimeirospringboot.repository.TurmaRepository;
import br.edu.br.meuprimeirospringboot.service.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

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

    @Override
    @Transactional
    public void matricularAlunos(Long turmaId, List<Long> alunosIds) {
        Turma turma = turmaRepository.findById(turmaId)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        List<Aluno> alunosParaMatricular = alunoRepository.findAllById(alunosIds);
        
        if (turma.getAlunos() == null) {
            turma.setAlunos(new HashSet<>());
        }

        for (Aluno aluno : alunosParaMatricular) {
            turma.getAlunos().add(aluno);
            if (aluno.getTurmas() == null) {
                aluno.setTurmas(new HashSet<>());
            }
            aluno.getTurmas().add(turma);
        }
        
        turmaRepository.save(turma);
        alunoRepository.saveAll(alunosParaMatricular);
    }

    @Override
    @Transactional
    public void removerAluno(Long turmaId, Long alunoId) {
        Turma turma = turmaRepository.findById(turmaId)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
            
        Aluno aluno = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            
        turma.getAlunos().remove(aluno);
        turmaRepository.save(turma);
    }
}
