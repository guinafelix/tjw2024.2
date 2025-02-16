package br.edu.br.meuprimeirospringboot.service;

import java.util.List;
import java.util.Optional;

import br.edu.br.meuprimeirospringboot.entity.Turma;

public interface TurmaService {

    List<Turma> buscarTodas();

    Turma buscarPorId(Long id);

    void excluirPorId(Long id);

    Turma cadastrar(Turma turma);

    Turma editar(Turma turma);

    List<Turma> buscarPorDisciplina(Long disciplinaId);

    List<Turma> buscarPorSemestre(Long semestreId);

    Optional<Turma> buscarPorDisciplinaESemestre(Long disciplinaId, Long semestreId);

    void matricularAlunos(Long turmaId, List<Long> alunosIds);
}
