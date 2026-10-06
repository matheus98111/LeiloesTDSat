/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
    conn = new conectaDAO().connectDB();
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.executeUpdate();
        conn.close();
    } catch (Exception erro) {
        System.out.println("Erro ao cadastrar: " + erro.getMessage());
    }
}
        
        //conn = new conectaDAO().connectDB();
        
        
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
       conn = new conectaDAO().connectDB();
    String sql = "SELECT * FROM produtos";
    listagem.clear();

    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            listagem.add(produto);
        }
        conn.close();
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
    } 
        return listagem;
    }
   public void venderProduto(int id){
    conn = new conectaDAO().connectDB();
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try {
        prep = conn.prepareStatement(sql);
        prep.setInt(1, id);
        int linhas = prep.executeUpdate();
        conn.close();

        if (linhas > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com esse ID.");
        }
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + erro.getMessage());
    }
} 
    
 public ArrayList<ProdutosDTO> listarProdutosVendidos(){
    conn = new conectaDAO().connectDB();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    ArrayList<ProdutosDTO> vendidos = new ArrayList<>();

    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            vendidos.add(produto);
        }
        conn.close();
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + erro.getMessage());
    }
    return vendidos;
}   
        
}

