package desktop;

import javax.swing.*;
import java.awt.*;

public class TelaInicial {
    private JButton criarAtividade;
    private JButton lançarNota;
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

        lançarNota = new JButton("Lançar nota");
        lançarNota.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        lançarNota.setBounds(95, 200, 200, 60);
        inicialPanel.add(lançarNota);

        return inicialPanel;
    }

    public JButton getBotaoAtividade(){
        return criarAtividade;
    }

    public JButton getBotaoNota(){
        return lançarNota;
    }
}
