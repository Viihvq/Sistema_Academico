package web;
import dados.Queries;
import spark.Spark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    private static Connection conexao = null;
    private static Queries queriesDados = null;

    public static Connection conexaoBanco() throws SQLException {
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/sistema_academico", "root","tyui");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Conectando ao banco");
        conexao = conexaoBanco();
        queriesDados = new Queries(conexao); //"Repositorio de dados"

        Spark.get("/", (req, res) -> {
            return ViewUtil.renderiza("index.html");//criar: "botão consulta nota"
        });

        Spark.get("/checking", (req, res) -> {
            return ViewUtil.renderiza("consulta.html"); //criar: pega a matricula. Parecido com localiza-bilhete
        });

        //Spark pra exibir os dados


    }
}
