package web;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import dados.BancoQuery;
import dados.Fabrica;
import desktop.InterfacePrincipal;
import entidades.Aluno;
import entidades.Atividade;
import entidades.Professor;
import entidades.Turma;
import spark.Spark;

public class App {

	public static void main(String[] args) throws Exception {
		
		// Conectando com o banco
		Fabrica fab = new Fabrica();
		Connection conexao = fab.conectar();
		BancoQuery query = new BancoQuery(conexao);
				
		InterfacePrincipal ip = new InterfacePrincipal(conexao);
		ip.exibeInterface();
		
		Spark.get("/", (req, res) -> {
			return ViewUtil.renderiza("/visualiza-notas.html");
		});

		Spark.post("/localiza-matricula", (req, res) -> {
			String matricula = req.queryParams("matricula");
			// verificar se a matricula existe no banco de dados
			try {
				boolean existe = false;
				existe = query.buscaMatricula(matricula) != null; 
				if (existe) {
					res.redirect("/exibe-notas?matricula=" + matricula);
					return "";
				} else {
					return "Matricula não encontrada!";
				}
			}
			catch (Exception e) {
				return e.getClass().getName() + ": " +e.getMessage();
			}
			
		});
		
		Spark.get("/exibe-notas", (req, res) -> {
			try {
				String matricula = req.queryParams("matricula");
				Aluno aluno = query.buscaAluno(matricula);
				Atividade atividade = query.buscaAtividade(aluno.getAtividades().get(0).getId());
				Turma turma = query.buscaTurma(aluno.getId_turma().getId());
				Professor professor = query.buscaProfessor(atividade.getId_professor().getId());
				Map<String, Object> ctx = new HashMap<>();

				ctx.put("matricula", aluno.getMatricula());
				ctx.put("nome", aluno.getNome());
				ctx.put("email", aluno.getEmail());
				
				ctx.put("nome", turma.getNome());
				ctx.put("nome", atividade.getNome());
				ctx.put("nome", professor.getNome());
				ctx.put("valor_maximo", atividade.getValor_maximo());
				ctx.put("notas", atividade.getNotas());				
				
				
				return ViewUtil.renderiza("/exibe-notas.html", ctx);
			}
			catch (Exception e) {
				return e.getClass().getName() + ": " + e.getMessage();
			}
			
		});
	}
}
