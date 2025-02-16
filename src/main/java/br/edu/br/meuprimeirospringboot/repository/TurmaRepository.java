package br.edu.br.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.br.meuprimeirospringboot.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("select t from Turma t")
    List<Turma> findAllTurmas();

    @Query("select count(t) from Turma t")
    int countAllTurmas();

    @Query("select t from Turma t where t.disciplina.id = :disciplinaId")
    List<Turma> findTurmasByDisciplinaId(Long disciplinaId);

    @Query("select t from Turma t where t.semestre.id = :semestreId")
    List<Turma> findTurmasBySemestreId(Long semestreId);

    @Query("select t from Turma t where t.disciplina.id = :disciplinaId and t.semestre.id = :semestreId")
    Optional<Turma> findTurmaByDisciplinaAndSemestre(Long disciplinaId, Long semestreId);

    
}
