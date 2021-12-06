package desktop;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TelaInicial {
    private JButton criarAtividade;
    private JButton lancarNota;
    private JPanel inicialPanel;
    
    TelaInicial(){}

    public JPanel criaJPanelInicial(){
        inicialPanel = new JPanel();
        inicialPanel.setLayout(null);
        inicialPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        criarAtividade = new JButton("Criar atividade");
        criarAtividade.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        criarAtividade.setBounds(95, 90, 200, 60);
        inicialPanel.add(criarAtividade);

        lancarNota = new JButton("Lançar nota");
        lancarNota.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        lancarNota.setBounds(95, 200, 200, 60);
        inicialPanel.add(lancarNota);

        return inicialPanel;
    }

    public JButton getBotaoAtividade(){
        return criarAtividade;
    }

    public JButton getBotaoNota(){
        return lancarNota;
    }
}
