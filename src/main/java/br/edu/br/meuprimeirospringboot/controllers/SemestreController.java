package br.edu.br.meuprimeirospringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.br.meuprimeirospringboot.entity.Semestre;
import br.edu.br.meuprimeirospringboot.serviceImpl.SemestreServiceImpl;

@Controller
@RequestMapping("/semestres")
public class SemestreController {
    
    @Autowired
    private SemestreServiceImpl semestreService;
    
    @GetMapping("/listar")
    public String listarSemestres(ModelMap model) {
        model.addAttribute("semestres", semestreService.buscarTodos());
        model.addAttribute("titulo", "Listar Semestres");
        model.addAttribute("conteudo", "semestre/lista");
        return "index";
    }
    
    @GetMapping("/cadastrar")
    public String cadastrarSemestre(ModelMap model) {
        model.addAttribute("semestre", new Semestre());
        model.addAttribute("titulo", "Cadastrar Semestre");
        model.addAttribute("conteudo", "semestre/cadastro");
        return "index";
    }
    
    @PostMapping("/salvar")
    public String salvarSemestre(Semestre semestre) {
        semestreService.cadastrar(semestre);
        return "redirect:/semestres/listar";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirSemestre(@PathVariable("id") Long id) {
        semestreService.excluirPorId(id);
        return "redirect:/semestres/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String preEditarSemestre(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("semestre", semestreService.buscarPorId(id));
        return "/semestre/cadastro";
    }
    
    @PostMapping("/editar")
    public String editarSemestre(Semestre semestre) {
        semestreService.editar(semestre);
        return "redirect:/semestres/listar";
    }
}
