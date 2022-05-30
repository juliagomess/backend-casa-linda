package Conexao;

import com.example.demo.Movimentacao;
import com.example.demo.Produtos;
import javafx.fxml.FXML;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MovimentacaoDAO {
    Connection conn;

    public ResultSet verificaCod(Movimentacao objmovimentacao) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from produtos where cod = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,objmovimentacao.getCod());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet retornaPreco(Movimentacao objmovimentacao) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select valor_venda from produtos where cod = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,objmovimentacao.getCod());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    @FXML
    public void cadastrarMovimentacao(Movimentacao objmovimentacao) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "insert into movimentacoes(tipo,quantidade,cod,valor,data) values (?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objmovimentacao.getTipo());
            pstm.setInt(2, objmovimentacao.getQuantidade());
            pstm.setInt(3, objmovimentacao.getCod());
            pstm.setDouble(4, objmovimentacao.getValor());
            pstm.setString(5,objmovimentacao.getData());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
