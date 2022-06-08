package Controller;

import Conexao.ProdutosDAO;
import com.example.demo.LoginApplication;
import com.example.demo.Produtos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DetalhesProdutoController implements Initializable {
    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @FXML
    private TextField txtnome;
    @FXML
    private TextField txtpreco;
    @FXML
    private TextField txtfornecedor;
    @FXML
    private TextField txtgrupo;
    @FXML
    private TextArea txtdescricao;
    @FXML
    protected void voltarTela(ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("ListarProdutos.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Lista Produtos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void voltarTelaMenu(ActionEvent actionEvent) {
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

    public void colocaTextField(int codigo){
        ProdutosDAO objprodutosdao =  new ProdutosDAO();
        ResultSet rsprodutosdao;
        rsprodutosdao= objprodutosdao.filtrarDetalhes(getCodigo());
        try {
            if(rsprodutosdao.next()) {
                txtnome.setText(rsprodutosdao.getString("nome"));
                txtfornecedor.setText(rsprodutosdao.getString("fornecedor"));
                txtpreco.setText(rsprodutosdao.getString("valor_venda"));
                txtgrupo.setText(rsprodutosdao.getString("categoria"));
                txtdescricao.setText(rsprodutosdao.getString("descricao"));
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
    @FXML
    public void deletaProduto(ActionEvent actionEvent){
        ProdutosDAO objprodutosdao = new ProdutosDAO();
        objprodutosdao.deletarProduto(getCodigo());
        JOptionPane.showMessageDialog(null,"Produto deletado com sucesso!");
        voltarTela(actionEvent);
    }

    @FXML
    public void editaProduto(ActionEvent actionEvent){
        Produtos p = new Produtos();
        p.setNome(txtnome.getText());
        p.setCategoria(txtgrupo.getText());
        p.setFornecedor(txtfornecedor.getText());
        p.setValor_venda(Double.parseDouble(txtpreco.getText()));
        p.setDescricao(txtdescricao.getText());
        p.setCod(getCodigo());
        ProdutosDAO objprodutosdao = new ProdutosDAO();
        objprodutosdao.editaProduto(p);
        JOptionPane.showMessageDialog(null,"Produto editado com sucesso!");
        voltarTela(actionEvent);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Produtos.addOnChangeScreenListener(new Produtos.OnChangeScreen() {
            @Override
            public void OnScreenChanged(int cod) {
                setCodigo(cod);
                colocaTextField(cod);
            }
        });

    }
}

