package br.edu.br.meuprimeirospringboot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.br.meuprimeirospringboot.entity.Disciplina;
import br.edu.br.meuprimeirospringboot.repository.DisciplinaRepository;
import br.edu.br.meuprimeirospringboot.service.DisciplinaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    public List<Disciplina> buscarTodas() {
        return disciplinaRepository.findAll();
    }

    @Override
    public Disciplina buscarPorId(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }

    @Override
    public void excluirPorId(Long id) {
        disciplinaRepository.deleteById(id);
    }

    @Override
    public Disciplina cadastrar(Disciplina d) {
        return disciplinaRepository.save(d);
    }

    @Override
    public Disciplina editar(Disciplina d) {
        return disciplinaRepository.save(d);
    }
}
