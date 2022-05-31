package Controller;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import Conexao.ProdutosDAO;
import com.example.demo.Produtos;
import com.example.demo.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

import java.io.IOException;
import java.sql.ResultSet;


public class CadastraProdutosController{


    @FXML
    private TextField txtnome;
    @FXML
    private TextField txtfornecedor;
    @FXML
    private TextField txtcategoria;
    @FXML
    private TextArea txtdescricao;
    @FXML
    private TextField txtpreco;
    @FXML
    protected void voltarTela(ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("MenuProduto.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("MenuProduto");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void cadastrar(ActionEvent actionEvent){
            Produtos p = new Produtos();
            try {
                p.setNome(txtnome.getText());
                p.setCategoria(txtcategoria.getText());
                p.setFornecedor(txtfornecedor.getText());
                p.setValor_venda(Integer.parseInt(txtpreco.getText()));
                p.setDescricao(txtdescricao.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Existem campos obrigatórios não preenchidos");
                return;
            }

            ProdutosDAO objprodutosdao = new ProdutosDAO();
            ResultSet rsprodutosdao = objprodutosdao.verificaNome(p);
            try {
                if(rsprodutosdao.next()) {
                    JOptionPane.showMessageDialog(null, "Produto já cadastrado");
                    txtnome.setText("");
                }
                else {
                    objprodutosdao.cadastrarProdutos(p);
                    JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso");
                    txtnome.setText("");
                    txtpreco.setText("");
                    txtfornecedor.setText("");
                    txtdescricao.setText("");
                    txtcategoria.setText("");
                }
            }catch(Exception e){
                System.out.println(e);
            }
    }
}
