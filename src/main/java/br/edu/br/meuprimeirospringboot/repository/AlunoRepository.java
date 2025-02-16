package br.edu.br.meuprimeirospringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.br.meuprimeirospringboot.entity.Aluno;


public interface AlunoRepository extends JpaRepository<Aluno,Long> {
	
	@Query("select a from Aluno a")
	List<Aluno> findAllAlunos();
	
	@Query("select count(a) from Aluno a")
	int countAllAlunos();
	
	@Query("select a from Aluno a where a.email = :email")
	Optional<Aluno> findAlunoByEmailOptional(String email);
	
	@Query("""
			SELECT a FROM Aluno a 
			WHERE a NOT IN (
					SELECT al FROM Turma t 
					JOIN t.alunos al 
					WHERE t.id = :turmaId
			)
			ORDER BY a.nome
			""")
	List<Aluno> findAlunosNaoMatriculados(@Param("turmaId") Long turmaId);
}
