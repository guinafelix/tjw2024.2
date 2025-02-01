package br.edu.br.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.br.meuprimeirospringboot.entity.Semestre;

public interface SemestreRepository extends JpaRepository<Semestre, Long> {

    @Query("select s from Semestre s")
    List<Semestre> findAllSemestres();

    @Query("select count(s) from Semestre s")
    int countAllSemestres();

    @Query("select s from Semestre s where s.ano = :ano and s.semestre = :semestre")
    Optional<Semestre> findSemestreByAnoAndSemestre(String ano, Integer semestre);
}
