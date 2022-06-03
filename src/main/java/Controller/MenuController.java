package Controller;

import com.example.demo.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    ///ADICIONAR TELA ESTOQUE
    @FXML
    protected void EstoqueTela(ActionEvent actionEvent) {
        try {


            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();

            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("Estoque.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Em estoque");
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void ProdutoTela(ActionEvent actionEvent) {
        try {


            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();

            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("MenuProduto.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Menu produto");
                    stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void MovimentacoesTela(ActionEvent actionEvent) {
        try {


            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();

            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("MenuMovimentacoes.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Menu movimentações");
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ADICIONAR TELA RELATORIO
    protected void RelatorioTela(ActionEvent actionEvent) {
        try {


            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();

            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("RelatorioTela.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Produto");
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
