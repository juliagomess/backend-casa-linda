package Controller;

import Conexao.UsuarioDAO;
import com.example.demo.Login;
import com.example.demo.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecuperarSenhaController {
    @FXML
    private TextField txtemail;

    @FXML
    protected void voltarTelaLogin(ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void recuperaSenha(ActionEvent actionEvent){
        try {
            String email = txtemail.getText();
            Login lg = new Login();
            lg.setEmail(email);

            UsuarioDAO objusuariodao = new UsuarioDAO();
            ResultSet rsusuariodao = objusuariodao.recuperaSenha(lg);
            if(rsusuariodao.next()){
                JOptionPane.showMessageDialog(null,"Enviamos um email para você recuperar sua senha!");
                txtemail.setText("");
            }else{
                JOptionPane.showMessageDialog(null,"Email não cadastrado");
                txtemail.setText("");
            }

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
