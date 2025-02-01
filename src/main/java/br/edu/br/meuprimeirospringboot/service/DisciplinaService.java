package br.edu.br.meuprimeirospringboot.service;

import java.util.List;

import br.edu.br.meuprimeirospringboot.entity.Disciplina;

public interface DisciplinaService {
    
    List<Disciplina> buscarTodas();
    
    Disciplina buscarPorId(Long id);
    
    void excluirPorId(Long id);
    
    Disciplina cadastrar(Disciplina d);
    
    Disciplina editar(Disciplina d);
}
