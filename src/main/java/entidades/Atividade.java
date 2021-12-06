package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Atividade {
    private Integer id;
    private String nome;
    private Float nota_aluno;
    private Float valor_maximo;
    private Date data_de_entrega;
    private Professor id_professor;
    private List<Float> notas = new ArrayList<Float>();
    

    public Atividade() {
    }
   
    
    public Atividade(Integer id, String nome, Float nota_aluno, Float valor_maximo, List<Float> notas) {
		super();
		this.id = id;
		this.nome = nome;
		this.nota_aluno = nota_aluno;
		this.valor_maximo = valor_maximo;
		this.notas = notas;
	}



	public Atividade(Integer id, String nome, Float nota_aluno, Float valor_maximo, Date data_de_entrega,
			Professor id_professor) {
		this.id = id;
		this.nome = nome;
		this.nota_aluno = nota_aluno;
		this.valor_maximo = valor_maximo;
		this.data_de_entrega = data_de_entrega;
		this.id_professor = id_professor;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getNota_aluno() {
        return nota_aluno;
    }

    public void setNota_aluno(Float nota_aluno) {
        this.nota_aluno = nota_aluno;
    }

    public Float getValor_maximo() {
        return valor_maximo;
    }

    public void setValor_maximo(Float valor_maximo) {
        this.valor_maximo = valor_maximo;
    }

    public Date getData_de_entrega() {
        return data_de_entrega;
    }

    public void setData_de_entrega(Date data_de_entrega) {
        this.data_de_entrega = data_de_entrega;
    }

    public Professor getId_professor() {
        return id_professor;
    }

    public void setId_professor(Professor id_professor) {
        this.id_professor = id_professor;
    }

	public List<Float> getNotas() {
		return notas;
	}

	public void setNotas(List<Float> notas) {
		this.notas = notas;
	}
    
    
}
