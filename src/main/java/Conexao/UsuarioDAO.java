package Conexao;

import com.example.demo.Login;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    Connection conn;
    public ResultSet autenticacaoUsuario(Login objlogin) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from usuario where user = ? and senha = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objlogin.getUsuario());
            pstm.setString(2,objlogin.getSenha());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public ResultSet recuperaSenha(Login objlogin) {
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from usuario where email = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,objlogin.getEmail());
            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
