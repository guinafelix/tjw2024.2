package br.edu.br.meuprimeirospringboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_semestre")
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ano;  // Ex: 2024
    private Integer semestre;  // 1 para 1º semestre, 2 para 2º semestre
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @OneToMany(mappedBy = "semestre")
    private List<Turma> turmas;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
