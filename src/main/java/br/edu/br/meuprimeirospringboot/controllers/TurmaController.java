package br.edu.br.meuprimeirospringboot.controllers;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.edu.br.meuprimeirospringboot.entity.Aluno;
import br.edu.br.meuprimeirospringboot.entity.Turma;
import br.edu.br.meuprimeirospringboot.serviceImpl.AlunoServiceImpl;
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

    @Autowired
    private AlunoServiceImpl alunoService;
    
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
        model.addAttribute("disciplinas", disciplinaService.buscarTodas());
        model.addAttribute("semestres", semestreService.buscarTodos());
        model.addAttribute("professores", professorServiceImpl.buscarTodos());
        model.addAttribute("titulo", "Editar Turma");
        model.addAttribute("conteudo", "turma/cadastro");
        return "index";
    }
    
    @PostMapping("/editar")
    public String editarTurma(Turma turma) {
        turmaService.editar(turma);
        return "redirect:/turmas/listar";
    }

     @GetMapping("/matricular/{id}")
    public String mostrarTelaMatricula(@PathVariable("id") Long turmaId, ModelMap model) {
        try {
            Turma turma = turmaService.buscarPorId(turmaId);
            if (turma == null) {
                return "redirect:/turmas/listar";
            }
            
            List<Aluno> alunosDisponiveis = alunoService.buscarAlunosNaoMatriculados(turmaId);
            
            model.addAttribute("turma", turma);
            model.addAttribute("alunosDisponiveis", alunosDisponiveis);
            model.addAttribute("titulo", "Matricular Alunos");
            model.addAttribute("conteudo", "turma/matricular");
            
            return "index";
        } catch (Exception e) {
            return "redirect:/turmas/listar";
        }
    }
    
    @PostMapping("/matricular/salvar")
    public String salvarMatriculas(@RequestParam("turmaId") Long turmaId,
                                 @RequestParam(value = "alunosIds", required = false) List<Long> alunosIds,
                                 RedirectAttributes attr) {
        try {
            if (alunosIds == null || alunosIds.isEmpty()) {
                attr.addFlashAttribute("mensagem", "Selecione pelo menos um aluno para matricular.");
                return "redirect:/turmas/matricular/" + turmaId;
            }
            turmaService.matricularAlunos(turmaId, alunosIds);
            attr.addFlashAttribute("mensagem", "Alunos matriculados com sucesso!");
            
            return "redirect:/turmas/listar";
        } catch (Exception e) {
            attr.addFlashAttribute("mensagem", "Erro ao matricular alunos: " + e.getMessage());
            return "redirect:/turmas/matricular/" + turmaId;
        }
    }

    @GetMapping("/{id}/alunos")
    public String listarAlunosTurma(@PathVariable("id") Long turmaId, ModelMap model) {
        try {
            Turma turma = turmaService.buscarPorId(turmaId);
            if (turma == null) {
                return "redirect:/turmas/listar";
            }
            
            model.addAttribute("turma", turma);
            model.addAttribute("titulo", "Alunos da Turma");
            model.addAttribute("conteudo", "turma/alunos");
            
            return "index";
        } catch (Exception e) {
            return "redirect:/turmas/listar";
        }
    }

    @GetMapping("/{turmaId}/remover-aluno/{alunoId}")
    public String removerAlunoDaTurma(@PathVariable("turmaId") Long turmaId,
                                     @PathVariable("alunoId") Long alunoId,
                                     RedirectAttributes attr) {
        try {
            turmaService.removerAluno(turmaId, alunoId);
            attr.addFlashAttribute("mensagem", "Aluno removido da turma com sucesso!");
        } catch (Exception e) {
            attr.addFlashAttribute("mensagem", "Erro ao remover aluno: " + e.getMessage());
        }
        
        return "redirect:/turmas/" + turmaId + "/alunos";
    }

    @GetMapping("/gerar-relatorio")
    public ResponseEntity<byte[]> gerarRelatorioTurmasComAlunos() {
        List<Turma> turmas = turmaService.buscarTodas();

        byte[] pdfBytes = gerarPdfComTurmasEAlunos(turmas);

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=relatorio_turmas_com_alunos.pdf")
                .body(pdfBytes);
    }

     private byte[] gerarPdfComTurmasEAlunos(List<Turma> turmas) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("Relatório de alunos matriculados por turma"));
            document.add(new Paragraph(" "));
            
            for (Turma turma : turmas) {
                document.add(new Paragraph("Turma: " + turma.getId()));
                document.add(new Paragraph("Disciplina: " + turma.getDisciplina().getNome()));
                document.add(new Paragraph("Professor: " + turma.getProfessor().getNome()));
                document.add(new Paragraph("Semestre: " + turma.getSemestre().getAno() + " - " + turma.getSemestre().getSemestre()));
                document.add(new Paragraph(" "));

                document.add(new Paragraph("Alunos Matriculados:"));
                for (Aluno aluno : turma.getAlunos()) {
                    document.add(new Paragraph("Aluno: " + aluno.getNome()));
                }
                document.add(new Paragraph(" "));
            }
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
