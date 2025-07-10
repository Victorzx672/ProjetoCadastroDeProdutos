import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SistemaCadastro extends JFrame {
    private JTextField nomeField;
    private JTextField precoField;
    private JTextArea listaArea;
    private ArrayList<Produto> produtos;

    public SistemaCadastro() {
        super("Cadastro de Produtos");
        produtos = new ArrayList<>();

        setLayout(new BorderLayout());

        // Painel superior - cadastro
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Nome do Produto:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Preço (R$):"));
        precoField = new JTextField();
        inputPanel.add(precoField);

        JButton adicionarBtn = new JButton("Adicionar");
        JButton limparBtn = new JButton("Limpar");

        inputPanel.add(adicionarBtn);
        inputPanel.add(limparBtn);

        // Área de listagem
        listaArea = new JTextArea();
        listaArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaArea);

        // Adicionando os componentes à janela
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Ações dos botões
        adicionarBtn.addActionListener(e -> adicionarProduto());
        limparBtn.addActionListener(e -> limparCampos());

        // Configurações da janela
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void adicionarProduto() {
        String nome = nomeField.getText();
        String precoText = precoField.getText();

        if (nome.isEmpty() || precoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double preco = Double.parseDouble(precoText);
            Produto produto = new Produto(nome, preco);
            produtos.add(produto);
            atualizarLista();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarLista() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : produtos) {
            sb.append(p.toString()).append("\n");
        }
        listaArea.setText(sb.toString());
    }

    private void limparCampos() {
        nomeField.setText("");
        precoField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaCadastro::new);
    }
}