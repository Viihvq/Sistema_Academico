import desktop.InterfacePrincipal;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Principal {

//    public static Connection conexaoBanco() throws SQLException {
//        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/sistema_academico", "root","tyui");
//    }

    public static void main(String[] args) throws Exception {
        Conexao BD = new Conexao();
        Connection conexao = BD.conectar();
//        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new InterfacePrincipal(conexao);
    }
}
