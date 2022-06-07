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

    public ResultSet filtrarCod(Movimentacao objmovimentacao) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from movimentacoes where cod = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,objmovimentacao.getCod());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet filtrarNome(Produtos objprodutos) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from movimentacoes where cod = (select cod from produtos where nome = ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objprodutos.getNome());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet filtrarFonecedor(Produtos objprodutos) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from movimentacoes where cod in (select cod from produtos where fornecedor = ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objprodutos.getFornecedor());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet filtrarData(Movimentacao objmovimentacao) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from movimentacoes where data = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objmovimentacao.getData());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet filtrarTipo(Movimentacao objmovimentacao) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from movimentacoes where tipo = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objmovimentacao.getTipo());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet listarTodos(Movimentacao objmovimentacao){
        conn = new ConexaoDAO().conectaBD();
         try {
             String sql = "select * from movimentacoes";
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery();
             return rs;
         }catch(Exception e){
             System.out.print(e);
             return  null;
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
