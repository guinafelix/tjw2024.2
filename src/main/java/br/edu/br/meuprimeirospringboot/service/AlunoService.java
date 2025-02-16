package br.edu.br.meuprimeirospringboot.service;

import java.util.List;

import br.edu.br.meuprimeirospringboot.entity.Aluno;


public interface AlunoService {
	
	List<Aluno> buscarTodos();
	
	Aluno buscarPorId(Long id);
	
	void excluirPorId(Long id);
	
	Aluno cadastrar(Aluno a);
	
	Aluno editar(Aluno a);
	
	List<Aluno> buscarAlunosNaoMatriculados(Long turmaId);

}
