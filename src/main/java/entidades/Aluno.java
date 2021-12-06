package entidades;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private Integer matricula;
    private Turma id_turma;
    private String nome;
    private String email;
    private List<Atividade> atividades = new ArrayList<Atividade>();

    public Aluno() {
    }
 
    public Aluno(Integer matricula, String nome, String email, List<Atividade> atividades, Turma id_turma) {
		super();
		this.matricula = matricula;
		this.id_turma = id_turma;
		this.nome = nome;
		this.email = email;
		this.atividades = atividades;
	}

	public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Turma getId_turma() {
        return id_turma;
    }

    public void setId_turma(Turma id_turma) {
        this.id_turma = id_turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
    
    
}
