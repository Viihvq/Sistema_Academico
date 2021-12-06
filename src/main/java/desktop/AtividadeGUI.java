package desktop;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.BancoQuery;
import entidades.Atividade;

public class AtividadeGUI {
    private Connection conexao;
    private JPanel atividadePanel;
    private JTextField nomeAtividade;
    private JTextField prazoLimiteDia, prazoLimiteMes, prazoLimiteAno;
    private JTextField notaMaxima;
    private JButton criarAtividade;
    private Atividade atividadeNota;
    private Date data;

    public JPanel criaJPanelAtividade(Connection conexaoBanco){
        conexao = conexaoBanco;
        atividadePanel = new JPanel();
        atividadeNota = new Atividade();

        JPanel panelEdicao1 = new JPanel();
        panelEdicao1.setBorder(BorderFactory.createEmptyBorder(50,50,0,45));
        BoxLayout l = new BoxLayout(panelEdicao1, BoxLayout.X_AXIS);
        panelEdicao1.setLayout(l);

        panelEdicao1.add(new JLabel("Nome da Atividade: "));
        nomeAtividade = new JTextField();
        nomeAtividade.setColumns(10);
        panelEdicao1.add(nomeAtividade);

        JPanel panelEdicao2 = new JPanel();
        panelEdicao2.setBorder(BorderFactory.createEmptyBorder(35,30,0,50));
        BoxLayout l2 = new BoxLayout(panelEdicao2, BoxLayout.X_AXIS);
        panelEdicao2.setLayout(l2);

        panelEdicao2.add(new JLabel("Prazo limite de entrega: "));
        prazoLimiteDia = new JTextField();
        prazoLimiteDia.setColumns(2);
        panelEdicao2.add(prazoLimiteDia);
        panelEdicao2.add(new JLabel(" / "));
        prazoLimiteMes = new JTextField();
        prazoLimiteMes.setColumns(2);
        panelEdicao2.add(prazoLimiteMes);
        panelEdicao2.add(new JLabel(" / "));
        prazoLimiteAno = new JTextField();
        prazoLimiteAno.setColumns(3);
        panelEdicao2.add(prazoLimiteAno);

        JPanel panelEdicao3 = new JPanel();
        panelEdicao3.setBorder(BorderFactory.createEmptyBorder(35,90,30,50));
        BoxLayout l3 = new BoxLayout(panelEdicao3, BoxLayout.X_AXIS);
        panelEdicao3.setLayout(l3);

        panelEdicao3.add(new JLabel("Nota máxima: "));
        notaMaxima = new JTextField();

        notaMaxima.setColumns(10);
        panelEdicao3.add(notaMaxima);

        atividadePanel.add(panelEdicao1);
        atividadePanel.add(panelEdicao2);
        atividadePanel.add(panelEdicao3);

        criarAtividade = new JButton("Criar");
        atividadePanel.add(criarAtividade);

        getBotaoCriarAtividade();

        return atividadePanel;
    }

    public JButton getBotaoCriarAtividade(){
        criarAtividade.addActionListener((al) -> {

            try { //transformar em data
            System.out.println(nomeAtividade.getText()+" "+ Float.parseFloat(notaMaxima.getText()));

            if (nomeAtividade.getText().isBlank() || notaMaxima.getText().isBlank() || prazoLimiteDia.getText().isBlank() ||
                prazoLimiteMes.getText().isBlank() || prazoLimiteAno.getText().isBlank()){

                JOptionPane.showMessageDialog(null,
                "ERRO \nINFORMAÇÕES FALTANDO.",
                "ERROR",JOptionPane.ERROR_MESSAGE);
            }else {
                LocalDate data = LocalDate.of(Integer.valueOf(prazoLimiteAno.getText()),
                        Integer.valueOf(prazoLimiteMes.getText()),
                        Integer.valueOf(prazoLimiteDia.getText()));
                java.sql.Date dataSql = java.sql.Date.valueOf(data);
                BancoQuery conexaoAtividade = new BancoQuery(conexao);
                conexaoAtividade.cadastroAtividade(nomeAtividade.getText(), dataSql, Float.parseFloat(notaMaxima.getText()));

                JOptionPane.showMessageDialog(null,
                                              "     ATIVIDADE CRIADA.",
                                              "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
            }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"ERRO \nFAVOR TENTAR NOVAMENTE!",
                        "ERROR",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();

            }


        });
        return criarAtividade;
    }

}
