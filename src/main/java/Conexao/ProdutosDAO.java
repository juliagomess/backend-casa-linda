package Conexao;

import com.example.demo.Login;
import com.example.demo.Produtos;
import javafx.fxml.FXML;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutosDAO {
    Connection conn;

    public ResultSet verificaNome(Produtos objprodutos) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from produtos where nome = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objprodutos.getNome());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    @FXML
    public void cadastrarProdutos(Produtos objprodutos) {
        conn = new ConexaoDAO().conectaBD();
            try {
                String sql = "insert into produtos(nome,fornecedor, categoria, valor_venda, descricao) values (?,?,?,?,?)";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1, objprodutos.getNome());
                pstm.setString(2, objprodutos.getFornecedor());
                pstm.setString(3, objprodutos.getCategoria());
                pstm.setDouble(4, objprodutos.getValor_venda());
                pstm.setString(5, objprodutos.getDescricao());

                pstm.execute();
                pstm.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }

}
