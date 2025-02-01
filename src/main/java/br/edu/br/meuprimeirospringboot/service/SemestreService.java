package br.edu.br.meuprimeirospringboot.service;

import java.util.List;
import java.util.Optional;

import br.edu.br.meuprimeirospringboot.entity.Semestre;

public interface SemestreService {

    List<Semestre> buscarTodos();

    Semestre buscarPorId(Long id);

    void excluirPorId(Long id);

    Semestre cadastrar(Semestre semestre);

    Semestre editar(Semestre semestre);

    Optional<Semestre> buscarPorAnoESemestre(String ano, Integer semestre);
}
