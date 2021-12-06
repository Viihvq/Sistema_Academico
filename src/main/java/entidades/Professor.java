package entidades;

public class Professor {
    private Integer id;
    private Turma id_turma;
    private String nome;
    private String email;

    
    public Professor() {
	}
    
    public Professor(Integer id, String nome, String email, Turma id_turma) {
		this.id = id;
		this.id_turma = id_turma;
		this.nome = nome;
		this.email = email;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
