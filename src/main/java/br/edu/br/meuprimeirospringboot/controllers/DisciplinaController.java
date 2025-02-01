package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.br.meuprimeirospringboot.entity.Disciplina;
import br.edu.br.meuprimeirospringboot.serviceImpl.DisciplinaServiceImpl;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {
    
    @Autowired
    private DisciplinaServiceImpl disciplinaService;
    
    @GetMapping("/listar")
    public String listarDisciplinas(ModelMap model) {
        model.addAttribute("disciplinas", disciplinaService.buscarTodas());
        model.addAttribute("titulo", "Listar Disciplinas");
        model.addAttribute("conteudo", "disciplina/lista");
        return "index";
    }
    
    @GetMapping("/cadastrar")
    public String cadastrarDisciplina(ModelMap model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("titulo", "Cadastrar Disciplina");
        model.addAttribute("conteudo", "disciplina/cadastro");
        return "index";
    }
    
    @PostMapping("/salvar")
    public String salvarDisciplina(Disciplina disciplina) {
        disciplinaService.cadastrar(disciplina);
        return "redirect:/disciplinas/listar";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirDisciplina(@PathVariable("id") Long id) {
        disciplinaService.excluirPorId(id);
        return "redirect:/disciplinas/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String preEditarDisciplina(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("disciplina", disciplinaService.buscarPorId(id));
        return "/disciplina/cadastro";
    }
    
    @PostMapping("/editar")
    public String editarDisciplina(Disciplina disciplina) {
        disciplinaService.editar(disciplina);
        return "redirect:/disciplinas/listar";
    }
}
