package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.br.meuprimeirospringboot.entity.Professor;
import br.edu.br.meuprimeirospringboot.serviceImpl.ProfessorServiceImpl;

@Controller
@RequestMapping("/professores")
public class ProfessorController {
    
    @Autowired
    private ProfessorServiceImpl professorService;
    
    @GetMapping("/listar")
    public String listarProfessores(ModelMap model) {
        model.addAttribute("professores", professorService.buscarTodos());
        model.addAttribute("titulo", "Listar Professores");
        model.addAttribute("conteudo", "professor/lista");
        return "index";
    }
    
    @GetMapping("/cadastrar")
    public String cadastrarProfessor(ModelMap model) {
        model.addAttribute("professor", new Professor());
        model.addAttribute("titulo", "Cadastrar Professor");
        model.addAttribute("conteudo", "professor/cadastro");
        return "index";
    }
    
    @PostMapping("/salvar")
    public String salvarProfessor(Professor professor) {
        professorService.cadastrar(professor);
        return "redirect:/professores/listar";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirProfessor(@PathVariable("id") Long id) {
        professorService.excluirPorId(id);
        return "redirect:/professores/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String preEditarProfessor(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("professor", professorService.buscarPorId(id));
        model.addAttribute("conteudo", "professor/cadastro");
        model.addAttribute("titulo", "Editar Professor");
        return "index";
    }
    
    @PostMapping("/editar")
    public String editarProfessor(Professor professor) {
        professorService.editar(professor);
        return "redirect:/professores/listar";
    }
}
