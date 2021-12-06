package desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import dados.BancoQuery;

public class Notas {
    private JPanel notaPanel;
    private JTextField nomeAtividade;
    private JButton uploadArquivo;
    private JButton enviarNota;
    private JButton baixarModelo;
    private String dir = "";
    private Connection conexao;
    private String[] atividades;
    private String atividadeSelecionada = "";

    public JPanel criaJPanelNota(Connection conexaoBanco){
        conexao = conexaoBanco;
        notaPanel = new JPanel();

        JPanel panelEdicao1 = new JPanel();
        panelEdicao1.setBorder(BorderFactory.createEmptyBorder(50,50,0,45));
        BoxLayout l = new BoxLayout(panelEdicao1, BoxLayout.X_AXIS);
        panelEdicao1.setLayout(l);

        JLabel txtModelo = new JLabel("Planilha de notas pronta   ");
        panelEdicao1.add(txtModelo);
        baixarModelo = new JButton("Baixar modelo .csv");
        panelEdicao1.add(baixarModelo);

        JPanel panelEdicao2 = new JPanel();
        panelEdicao2.setBorder(BorderFactory.createEmptyBorder(50,30,50,45));
        BoxLayout l2 = new BoxLayout(panelEdicao2, BoxLayout.X_AXIS);
        panelEdicao2.setLayout(l2);

//        panelEdicao2.add(new JLabel("Nome da Atividade: "));
//        nomeAtividade = new JTextField();
//        nomeAtividade.setColumns(12);
//        panelEdicao2.add(nomeAtividade);

        try {
            BancoQuery conexaoAtividade = new BancoQuery(conexao);
            atividades = conexaoAtividade.getAtividade();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JComboBox atividadesExistentes = new JComboBox<>(atividades);
        panelEdicao2.add(atividadesExistentes);

        atividadesExistentes.addActionListener((al) ->{ //fazer condição pra quando nao selecionar nenhum.
                                                    //esse só ativa quando seleciona na mao
            atividadeSelecionada = String.valueOf(atividadesExistentes.getSelectedItem());
            System.out.println(atividadeSelecionada);
        });


        uploadArquivo = new JButton("Upload arquivo");
        panelEdicao2.add(uploadArquivo);

        enviarNota = new JButton("Lançar notas");

        notaPanel.add(panelEdicao1);
        notaPanel.add(panelEdicao2);
        notaPanel.add(enviarNota);

        getBotaoUpload();
        getBotaoModelo();
        return notaPanel;
    }

    public void getBotaoUpload(){
        uploadArquivo.addActionListener((al) -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Selecione arquivos .csv", "csv", "pdf" //pdc só pra teste
            );
            fileChooser.setFileFilter(filter);

            int retorno = fileChooser.showOpenDialog(null);

            if(retorno == JFileChooser.APPROVE_OPTION){
                dir = fileChooser.getSelectedFile().getAbsolutePath();
                nomeAtividade.setText(dir);
            }
        });
    }

    public void getBotaoModelo(){ //Fazer ma verificacao com o nome da atividade: combo box
        baixarModelo.addActionListener((al) -> {
//          String path_saves = System.getProperty("user.home");

            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Selecione um diretório para salvar a cópia do modelo: ");
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile().isDirectory()) {
                    System.out.println("You selected the directory: " + jfc.getSelectedFile());
                    File src = new File("src\\main\\java\\desktop\\Modelo.csv");
                    File target = new File(jfc.getSelectedFile() + "\\CopiaModelo.csv");

                    try {
                        copy(src, target);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        });
    }

    void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);  // Transferindo bytes de entrada para saída
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public String getDir() { //pego o dir pra saber onde esta o arquivo no pc do usuario
        return dir;
    }
}
