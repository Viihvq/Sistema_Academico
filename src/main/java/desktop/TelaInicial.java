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
//        inicialPanel.setBorder(BorderFactory.createEmptyBorder(30,50,50,50));

        criarAtividade = new JButton("Criar atividade");
        criarAtividade.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        criarAtividade.setBounds(95, 80, 200, 60); //---
        criarAtividade.setBackground(new Color(239, 246, 255));
        criarAtividade.setFocusPainted(false);
        inicialPanel.add(criarAtividade);

        lançarNota = new JButton("Lançar nota");
        lançarNota.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        lançarNota.setBounds(95, 170, 200, 60);//---
        lançarNota.setBackground(new Color(239, 246, 255));
        inicialPanel.add(lançarNota);

        inicialPanel.setBackground(new Color(5, 128, 176, 169));
        return inicialPanel;
    }

    public JButton getBotaoAtividade(){
        return criarAtividade;
    }

    public JButton getBotaoNota(){
        return lançarNota;
    }
}
