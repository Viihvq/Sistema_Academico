package entidades;

public class Nota {
    private Integer id;
    private Integer id_atividade;
    private Integer matricula_aluno;
    private Double nota_aluno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(Integer id_atividade) {
        this.id_atividade = id_atividade;
    }

    public Integer getMatricula_aluno() {
        return matricula_aluno;
    }

    public void setMatricula_aluno(Integer matricula_aluno) {
        this.matricula_aluno = matricula_aluno;
    }


    public Double getNota_aluno() {
        return nota_aluno;
    }

    public void setNota_aluno(Double nota_aluno) {
        this.nota_aluno = nota_aluno;
    }
}
