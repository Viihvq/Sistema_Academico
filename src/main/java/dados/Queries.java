package dados;

import entidades.Aluno;
import entidades.Atividade;
import entidades.Professor;
import javax.swing.*;
import java.sql.*;

public class Queries {
    private static Connection conexaoBD;
    Aluno aluno;
    Professor professor;
    Atividade atividade;
    static final String LISTAR_ALUNO = "SELECT matricula, nome, email, id_turma FROM ALUNO";
    static final String LISTAR_PROFESSOR = "SELECT matricula, nome, email, id_turma FROM ALUNO";
    //PARTE ALUNO SITE
    static final String LISTAR_NOTAS_ALUNO = "SELECT aluno.matricula, aluno.nome, atividade.nota_aluno FROM aluno " +
            "JOIN aluno_atividade ON (aluno.matricula = aluno_atividade.matricula_aluno) " +
            "JOIN atividade ON (aluno_atividade.id_atividade = atividade.id);";

    public Queries(Connection conexao){
        conexaoBD = conexao;
    }

    public static void cadastroAtividade(String nome, Date data_entrega, Double valor_max) throws SQLException {
        System.out.println("chegou");//DEBBUG
        PreparedStatement preparedStatement = conexaoBD.prepareStatement("INSERT INTO atividade (nome, data_de_entrega, valor_maximo) VALUES(?, ?, ?);");
        preparedStatement.setString(1, nome);
        preparedStatement.setDate(2,data_entrega);
        preparedStatement.setDouble(3,valor_max);
        preparedStatement.execute();
        System.out.println("entrou no cadastro");
    }

    public int getTotalAtividade() throws SQLException {
        PreparedStatement ps = conexaoBD.prepareStatement("Select count(*) from atividade;");
        ps.execute();
        ResultSet rsCount = ps.getResultSet();

        Integer contador = 0;

        if(rsCount.next()) {
            contador = rsCount.getInt(1);
            System.out.println(rsCount.getInt(1));
        }
        return contador;
    }

    public String[] getNomeAtividade() throws SQLException {
        PreparedStatement preparedStatement = conexaoBD.prepareStatement("SELECT nome FROM atividade;");
        preparedStatement.execute();
        ResultSet rs = preparedStatement.getResultSet();

        String[] atividades = new String[getTotalAtividade()];

        int contador = 0;
        while (rs.next()){
            atividades[contador] = rs.getString(1);
//            System.out.println(atividades[contador]); //DEBUG
            contador++;
        }

        return atividades;
    }

    public void getIdAtividade(String nomeAtividade) throws SQLException {
        PreparedStatement ps = conexaoBD.prepareStatement("SELECT id, valor_maximo FROM atividade WHERE nome='"+nomeAtividade+"';");
        ps.execute();
        ResultSet rs = ps.getResultSet();

        atividade = new Atividade();

        while (rs.next()){
            atividade.setId(rs.getInt(1));
            atividade.setValor_maximo(rs.getDouble(2));

            System.out.println(atividade.getId()+" "+atividade.getValor_maximo());
        }
    }

    public void getMatriculaAluno(String nomeAluno) throws SQLException {
        PreparedStatement ps = conexaoBD.prepareStatement("SELECT matricula FROM aluno WHERE nome='"+nomeAluno+"';");
        ps.execute();
        ResultSet rs = ps.getResultSet();

        aluno = new Aluno();

        while (rs.next()){
            aluno.setMatricula(rs.getInt(1));
            System.out.println("MATRICULA: "+aluno.getMatricula());
        }
    }

    public Integer setNotas(String nomeAluno, Double notaAluno, String nomeAtividade){
        Integer count=0;
        try {
            System.out.println("Nome aluno: "+nomeAluno+" Nota aluno: "+notaAluno+" Atividade: "+nomeAtividade); //DEBUG
            PreparedStatement ps = conexaoBD.prepareStatement("INSERT INTO nota (id_atividade, matricula_aluno, nota_aluno) VALUES (?,?,?);");

            getIdAtividade(nomeAtividade);
            getMatriculaAluno(nomeAluno);

            if (notaAluno > atividade.getValor_maximo()){
                JOptionPane.showMessageDialog(null,"Confira o arquivo novamente. " +
                        "\nNota do(a) aluno(a) "+nomeAluno+" não correspondente com o valor estipulado",
                        "ERROR",JOptionPane.ERROR_MESSAGE);
                count = 1;
            }else{
                ps.setInt(1,atividade.getId());
                ps.setInt(2,aluno.getMatricula());
                ps.setDouble(3,notaAluno);
                ps.execute();
                count = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void infoAluno(Aluno alunoArg) throws SQLException {
        PreparedStatement ps = conexaoBD.prepareStatement(LISTAR_ALUNO);
        ps.execute();
        ResultSet rs = ps.getResultSet();

//        aluno = alunoArg;

        while (rs.next()){
            aluno.setMatricula(rs.getInt(1));
            aluno.setNome(rs.getString(2));
            aluno.setEmail(rs.getString(3));
            aluno.setId_turma(rs.getInt(4));
        }
    }

    public void infoProfessor(Professor profArg) throws SQLException {
        PreparedStatement ps = conexaoBD.prepareStatement(LISTAR_PROFESSOR);
        ps.execute();
        ResultSet rs = ps.getResultSet();

        professor = profArg;

        while (rs.next()){
            professor.setId(rs.getInt(1));
            professor.setNome(rs.getString(2));
            professor.setEmail(rs.getString(3));
            professor.setId_turma(rs.getInt(4));
        }
    }

}
