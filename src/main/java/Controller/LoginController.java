package Controller;

import Conexao.ConexaoDAO;
import Conexao.UsuarioDAO;
import com.example.demo.Login;
import com.example.demo.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.TextField;

import javax.swing.*;

public class LoginController {

    @FXML
    private TextField txtusuario;

    @FXML
    private PasswordField txtsenha;
    @FXML
    protected void proximaTela(ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("MenuPrincipal.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void recsenhaTela(ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("RecuperarSenha.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Recuperar senha");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void verificaLogin(ActionEvent actionEvent){
        try {
            String nome = txtusuario.getText();
            String senha = txtsenha.getText();
            Login lg = new Login();
            lg.setUsuario(nome);
            lg.setSenha(senha);

            UsuarioDAO objusuariodao = new UsuarioDAO();
            ResultSet rsusuariodao = objusuariodao.autenticacaoUsuario(lg);
            if(rsusuariodao.next()){
                proximaTela(actionEvent);
            }else{
                JOptionPane.showMessageDialog(null,"Login ou senha incorretos");
                txtusuario.setText("");
                txtsenha.setText("");
            }

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
