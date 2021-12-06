package dados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import entidades.Aluno;
import entidades.Atividade;
import entidades.Professor;
import entidades.Turma;

public class BancoQuery {

	private Connection conexaoBD = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	private Aluno aluno;
	private Professor professor;

	static final String LISTAR_ALUNO = "SELECT matricula, nome, email, id_turma FROM ALUNO";
	static final String LISTAR_PROFESSOR = "SELECT matricula, nome, email, id_turma FROM ALUNO";
	// PARTE ALUNO SITE
	static final String LISTAR_NOTAS_ALUNO = "SELECT aluno.matricula, aluno.nome, atividade.nota_aluno " + "FROM aluno "
			+ "JOIN aluno_atividade ON (aluno.matricula = aluno_atividade.matricula_aluno) "
			+ "JOIN atividade ON (aluno_atividade.id_atividade = atividade.id);";

	public BancoQuery(Connection conexao) {
		this.conexaoBD = conexao;
	}

	// aqui vou ter que colocar de padr√£o id_professor 1, j√° que n√£o tem como
	// saber qual professor ta lan√ßando a atividade
	public void cadastroAtividade(String nome, Date data_entrega, Float valor_max) throws SQLException {
		System.out.println("chegou");// deve dar zika aqui embaixo. NOTA TA COMO INT
		try {
			st = conexaoBD.prepareStatement(
					"INSERT INTO atividade (nome, data_de_entrega, valor_maximo) " + "VALUES(?, ?, ?)");
			st.setString(1, nome);
			st.setDate(2, data_entrega);
			st.setFloat(3, valor_max);
			st.execute();
			System.out.println("entrou no cadastro");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[] getAtividade() throws SQLException {
		st = conexaoBD.prepareStatement("SELECT nome FROM atividade;");
		st.execute();
		rs = st.getResultSet();
		ResultSet rsCount = st.executeQuery("Select Count(*) from atividade");

		int contador = 0;
		if (rsCount.next()) {
			contador = rsCount.getInt(1);
		}

		System.out.println(contador);

		String[] atividades = new String[contador];
		contador = 0;
		while (rs.next()) {
			atividades[contador] = rs.getString(1);
//            System.out.println(atividades[contador]);
			contador++;
		}

		return atividades;
	}

	/*public void infoAluno(Aluno alunoArg) throws SQLException {
		st = conexaoBD.prepareStatement(LISTAR_ALUNO);
		st.execute();
		rs = st.getResultSet();

		aluno = alunoArg;

		while (rs.next()) {
			aluno.setMatricula(rs.getInt(1));
			aluno.setNome(rs.getString(2));
			aluno.setEmail(rs.getString(3));
			aluno.setId_turma(rs.getInt(4));
		}
	}

	public void infoProfessor(Professor profArg) throws SQLException {
		st = conexaoBD.prepareStatement(LISTAR_PROFESSOR);
		st.execute();
		rs = st.getResultSet();

		professor = profArg;

		while (rs.next()) {
			professor.setId(rs.getInt(1));
			professor.setNome(rs.getString(2));
			professor.setEmail(rs.getString(3));
			professor.setId_turma(rs.getInt(4));
		}
	}*/

	// MÈtodo que pega uma lista de notas dos alunos do banco
	public Aluno listarNotasAlunos(Aluno al) {
		try {
			st = conexaoBD.prepareStatement(LISTAR_NOTAS_ALUNO);
			st.setFloat(1, al.getAtividades().get(0).getNota_aluno());
			st.execute();
			rs = st.getResultSet();

			while (rs.next()) {
				int id_atividade = rs.getInt("id");
				String nome_atividade = rs.getString("nome");
				float nota_aluno = rs.getFloat("nota_aluno");
				float valor_maximo = rs.getFloat("valor_maximo");
				Atividade at = new Atividade(id_atividade, nome_atividade, nota_aluno, valor_maximo, null);
				at.getNotas().add(rs.getFloat("nota_aluno"));
				al.getAtividades().add(at);
			}
			return al;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR :" + e.getSQLState(), "DATABASE ERROR: ",
					JOptionPane.ERROR_MESSAGE);
		}
		return al;
	}

	// MÈtodo para buscar uma matricula no banco
	public Aluno buscaMatricula(String matriculaa) {
		int matricula = Integer.parseInt(matriculaa);
		try {
			st = conexaoBD.prepareStatement("SELECT * FROM aluno WHERE matricula = ?");
			st.setInt(1, matricula);
			st.execute();
			rs = st.getResultSet();

			while (rs.next()) {
				int matricula_aluno = rs.getInt("matricula");
				Aluno aluno = new Aluno(matricula_aluno, null, null, null, null);
				return aluno;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Matricula n„o encontrada!");
		}
		return null;

	}
	
	// MÈtodo para buscar um professor no banco
			public Professor buscaProfessor(int idProfessor) {
				try {
					st = conexaoBD.prepareStatement("SELECT * FROM professor WHERE id = ?");
					st.setInt(1, idProfessor);
					st.execute();
					rs = st.getResultSet();

					if (rs.next()) {
						int idzinho = rs.getInt("id");
						String nomezinho = rs.getString("nome");
						String emailzinho = rs.getString("email");
						int id_turma = rs.getInt("id_turma");
						Turma turmazinha = buscaTurma(id_turma);
						Professor professorzinho = new Professor(idzinho, nomezinho, emailzinho, turmazinha);
						return professorzinho;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Turma n„o encontrada!");
				}
				return null;
			}
	
	// MÈtodo para buscar uma turma no banco
		public Turma buscaTurma(int idTurma) {
			try {
				st = conexaoBD.prepareStatement("SELECT * FROM turma WHERE id = ?");
				st.setInt(1, idTurma);
				st.execute();
				rs = st.getResultSet();

				if (rs.next()) {
					int idzinho = rs.getInt("id");
					String nomezinho = rs.getString("nome");
					Turma turmazinha = new Turma(idzinho, nomezinho);
					return turmazinha;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Turma n„o encontrada!");
			}
			return null;
		}
	
	// MÈtodo para buscar uma atividade no banco
		public Atividade buscaAtividade(int idAtividade) {
			try {
				st = conexaoBD.prepareStatement("SELECT * FROM atividade WHERE id = ?");
				st.setInt(1, idAtividade);
				st.execute();
				rs = st.getResultSet();

				if (rs.next()) {
					int idzinho = rs.getInt("id");
					String nomezinho = rs.getString("nome");
					float nota_aluno = rs.getFloat("nota_aluno");
					float valor_maximo = rs.getFloat("valor_maximo");
					Date data_entrega = rs.getDate("data_entrega");
					int id_professor = rs.getInt("id_professor");
					Professor professorzinho = buscaProfessor(id_professor);
					Atividade atividadezinha = new Atividade(idzinho, nomezinho, nota_aluno, valor_maximo, data_entrega, professorzinho);
					return atividadezinha;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Atividade n„o encontrada!");
			}
			return null;
		}


	// MÈtodo para buscar um aluno no banco
	public Aluno buscaAluno(String matriculaa) {
		int matricula = Integer.parseInt(matriculaa);
		try {
			st = conexaoBD.prepareStatement("SELECT * FROM aluno WHERE matricula = ?");
			st.setInt(1, matricula);
			st.execute();
			rs = st.getResultSet();

			if (rs.next()) {
				int matriculazinha = rs.getInt("matricula");
				String nomezinho = rs.getString("nome");
				String emailzinho = rs.getString("email");
				int id_turma = rs.getInt("id_turma");
				Atividade listaAtividades;// sla
				Turma turma = buscaTurma(id_turma);
				Aluno alunozinho = new Aluno(matriculazinha, nomezinho, emailzinho, null, turma);
				return alunozinho;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Aluno n„o encontrado!");
		}
		return null;
	}
}
