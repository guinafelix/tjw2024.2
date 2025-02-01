package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.br.meuprimeirospringboot.entity.Turma;
import br.edu.br.meuprimeirospringboot.serviceImpl.DisciplinaServiceImpl;
import br.edu.br.meuprimeirospringboot.serviceImpl.ProfessorServiceImpl;
import br.edu.br.meuprimeirospringboot.serviceImpl.SemestreServiceImpl;
import br.edu.br.meuprimeirospringboot.serviceImpl.TurmaServiceImpl;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
    
    @Autowired
    private TurmaServiceImpl turmaService;

    @Autowired
    private DisciplinaServiceImpl disciplinaService;

    @Autowired
    private SemestreServiceImpl semestreService;

    @Autowired
    private ProfessorServiceImpl professorServiceImpl;
    
    @GetMapping("/listar")
    public String listarTurmas(ModelMap model) {
        model.addAttribute("turmas", turmaService.buscarTodas());
        model.addAttribute("disciplinas", disciplinaService.buscarTodas());
        model.addAttribute("titulo", "Listar Turmas");
        model.addAttribute("conteudo", "turma/lista");
        return "index";
    }
    
    @GetMapping("/cadastrar")
    public String cadastrarTurma(ModelMap model) {
        model.addAttribute("turma", new Turma());
        model.addAttribute("disciplinas", disciplinaService.buscarTodas());
        model.addAttribute("semestres", semestreService.buscarTodos());
        model.addAttribute("professores", professorServiceImpl.buscarTodos());
        model.addAttribute("titulo", "Cadastrar Turma");
        model.addAttribute("conteudo", "turma/cadastro");
        return "index";
    }
    
    @PostMapping("/salvar")
    public String salvarTurma(Turma turma) {
        turmaService.cadastrar(turma);
        return "redirect:/turmas/listar";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirTurma(@PathVariable("id") Long id) {
        turmaService.excluirPorId(id);
        return "redirect:/turmas/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String preEditarTurma(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("turma", turmaService.buscarPorId(id));
        return "/turma/cadastro";
    }
    
    @PostMapping("/editar")
    public String editarTurma(Turma turma) {
        turmaService.editar(turma);
        return "redirect:/turmas/listar";
    }
}
