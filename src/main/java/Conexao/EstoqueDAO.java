package Conexao;

import com.example.demo.Estoque;
import com.example.demo.Movimentacao;
import javafx.fxml.FXML;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDAO {
    Connection conn;
    public ResultSet encontraQuantidade(Movimentacao objmovimentacao){
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from estoque where cod = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,objmovimentacao.getCod());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void adicionaEstoque(Estoque objestoque){
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "update estoque set quantidade = ? where cod = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,objestoque.getQuantidade());
            pstm.setInt(2,objestoque.getCod());
            pstm.execute();
            pstm.close();


        }catch(Exception e){
            System.out.println(e);

        }
    }
    @FXML
    public void acrescentaEstoque(Movimentacao objmovimentacao) {
        conn = new ConexaoDAO().conectaBD();
        Estoque e = new Estoque();
        EstoqueDAO objestoquedao = new EstoqueDAO();
        ResultSet rsestoquedao = objestoquedao.encontraQuantidade(objmovimentacao);
        try {
            if(rsestoquedao.next()) {
                e.setQuantidade(objmovimentacao.getQuantidade() + rsestoquedao.getInt("quantidade"));
                e.setCod(objmovimentacao.getCod());
                adicionaEstoque(e);

            }
        }catch(Exception er){
            System.out.println(er);
        }
    }

    public void removeEstoque(Estoque objestoque){
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "update estoque set quantidade = ? where cod = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,objestoque.getQuantidade());
            pstm.setInt(2,objestoque.getCod());
            pstm.execute();
            pstm.close();


        }catch(Exception e){
            System.out.println(e);

        }
    }

    public boolean verificaSaida(Movimentacao objmovimentacao, Estoque objestoque){
        if(objmovimentacao.getQuantidade()>objestoque.getQuantidade()){
            JOptionPane.showMessageDialog(null,"Quantidade indisponivel");
            return false;
        }
        return true;
    }

    @FXML
    public boolean retirarEstoque(Movimentacao objmovimentacao) {
        conn = new ConexaoDAO().conectaBD();
        Estoque objestoque = new Estoque();
        EstoqueDAO objestoquedao = new EstoqueDAO();
        ResultSet rsestoquedao = objestoquedao.encontraQuantidade(objmovimentacao);
        try {
            if(rsestoquedao.next()) {
                objestoque.setQuantidade(rsestoquedao.getInt("quantidade") - objmovimentacao.getQuantidade());
                objestoque.setCod(objmovimentacao.getCod());
                if(verificaSaida(objmovimentacao, objestoque)) {
                    removeEstoque(objestoque);
                    return true;
                }
            }
        }catch(Exception er){
            System.out.println(er);
        }
        return false;
    }

    public void verificaQuantidade(Movimentacao objmovimentacao){
        EstoqueDAO objestoquedao = new EstoqueDAO();
        ResultSet rsestoquedao = objestoquedao.encontraQuantidade(objmovimentacao);
        try {
            if (rsestoquedao.next()) {
                if (rsestoquedao.getInt("quantidade") <= 10) {
                    JOptionPane.showMessageDialog(null,"O produto "+objmovimentacao.getCod()+" está acabando!");
                }
            }
        }catch(Exception er){
            System.out.println(er);
        }

    }

    public ResultSet filtrarCod(Estoque objestoque) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from estoque where cod = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,objestoque.getCod());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet filtrarNomeCod(String nome) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from estoque where nome = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,nome);
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }



    public ResultSet filtrarNome(Estoque objestoque) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from estoque where nome = ? ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objestoque.getNome());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ResultSet listarTodos(Estoque objestoque){
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from estoque";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch(Exception e){
            System.out.print(e);
            return  null;
        }
    }
}
