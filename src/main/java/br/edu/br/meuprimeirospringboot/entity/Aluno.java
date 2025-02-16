package br.edu.br.meuprimeirospringboot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tbl_aluno")
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "nomeAluno")
	private String nome;
	private String matricula;
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco e;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "aluno", cascade = CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Professor> professores;
	
	@Temporal(TemporalType.TIME)
	private Date dtNascimento;
	
	@ManyToMany(mappedBy = "alunos", fetch = FetchType.LAZY)
  private Set<Turma> turmas = new HashSet<>();
	
	@Transient
	private int idade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Endereco getE() {
		return e;
	}
	public void setE(Endereco e) {
		this.e = e;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public List<Professor> getProfessores() {
		return professores;
	}
	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	
	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
			if (this.turmas != null) {
					for (Turma turma : this.turmas) {
							turma.getAlunos().remove(this);
					}
			}
			this.turmas = turmas;
			if (turmas != null) {
					for (Turma turma : turmas) {
							turma.getAlunos().add(this);
					}
			}
	}

	public void addTurma(Turma turma) {
			this.turmas.add(turma);
			turma.getAlunos().add(this);
	}

	public void removeTurma(Turma turma) {
			this.turmas.remove(turma);
			turma.getAlunos().remove(this);
	}

}
