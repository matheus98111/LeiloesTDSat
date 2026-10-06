import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {

    private JTable tabelaVendas;

    public vendasVIEW() {
        setTitle("Produtos Vendidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(520, 380);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Produtos Vendidos", JLabel.CENTER);
        titulo.setFont(new Font("Lucida Fax", 0, 18));
        add(titulo, BorderLayout.NORTH);

        tabelaVendas = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nome", "Valor", "Status"}
        ));
        add(new JScrollPane(tabelaVendas), BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        add(btnVoltar, BorderLayout.SOUTH);

        listarVendidos();
    }

    private void listarVendidos() {
        ProdutosDAO produtosdao = new ProdutosDAO();
        DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
        model.setNumRows(0);

        ArrayList<ProdutosDTO> vendidos = produtosdao.listarProdutosVendidos();

        for (int i = 0; i < vendidos.size(); i++) {
            model.addRow(new Object[]{
                vendidos.get(i).getId(),
                vendidos.get(i).getNome(),
                vendidos.get(i).getValor(),
                vendidos.get(i).getStatus()
            });
        }
    }
}