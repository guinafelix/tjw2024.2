package br.edu.br.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.br.meuprimeirospringboot.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
    @Query("select p from Professor p")
    List<Professor> findAllProfessores();
    
    @Query("select p from Professor p where p.id = :id")
    Optional<Professor> findProfessorById(Long id);

    @Query("select p from Professor p where p.nome = :nome")
    Optional<Professor> findProfessorByNome(String nome);
}
