package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.br.meuprimeirospringboot.entity.Semestre;
import br.edu.br.meuprimeirospringboot.repository.SemestreRepository;
import br.edu.br.meuprimeirospringboot.service.SemestreService;

@Service
public class SemestreServiceImpl implements SemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    @Override
    public List<Semestre> buscarTodos() {
        return semestreRepository.findAll();
    }

    @Override
    public Semestre buscarPorId(Long id) {
        Optional<Semestre> semestre = semestreRepository.findById(id);
        return semestre.orElse(null);  // Retorna null se não encontrado
    }

    @Override
    public void excluirPorId(Long id) {
        semestreRepository.deleteById(id);
    }

    @Override
    public Semestre cadastrar(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    @Override
    public Semestre editar(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    @Override
    public Optional<Semestre> buscarPorAnoESemestre(String ano, Integer semestre) {
        return semestreRepository.findSemestreByAnoAndSemestre(ano, semestre);
    }
}
