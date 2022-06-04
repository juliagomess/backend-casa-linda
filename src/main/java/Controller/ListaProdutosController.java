package Controller;

import Conexao.ProdutosDAO;
import com.example.demo.Categoria;
import com.example.demo.LoginApplication;
import com.example.demo.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListaProdutosController implements Initializable {
    @FXML
    private ComboBox<Categoria> filtro;
    @FXML
    private TextField pesquisa;
    private List<Categoria> categorias = new ArrayList<>();
    private ObservableList<Categoria> obsCategorias;

    @FXML
    private TableView<Produtos> tabela;
    @FXML
    private TableColumn<Produtos, Integer> tabelacod;
    @FXML
    private TableColumn<Produtos,String> tabelanome;
    @FXML
    private TableColumn<Produtos, Integer> tabelagrupo;
    @FXML
    private TableColumn<Produtos,String> tabeladetalhes;
    @FXML
    private TableColumn<Button,String> detalhes;

    ObservableList<Produtos> oblist  = FXCollections.observableArrayList();
    public void carregarCategorias(){
        Categoria categoria1 = new Categoria(1,"Codigo");
        Categoria categoria2 = new Categoria(2,"Nome");
        Categoria categoria3 = new Categoria(2,"Categoria");
        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        obsCategorias= FXCollections.observableArrayList(categorias);
        filtro.setItems(obsCategorias);
    }
    public String pegarCategoria(){
        Categoria categoria = filtro.getSelectionModel().getSelectedItem();
        return categoria.getNome();
    }
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
    public void filtrar() {
        tabela.getItems().clear();
        tabelacod.setCellValueFactory(new PropertyValueFactory("cod"));
        tabelanome.setCellValueFactory(new PropertyValueFactory("nome"));
        tabelagrupo.setCellValueFactory(new PropertyValueFactory("categoria"));
        String filtro = pegarCategoria();
        Produtos p = new Produtos();
        ProdutosDAO objprodutosdao =  new ProdutosDAO();
        ResultSet rsprodutosdao;
        if(filtro.equals("Codigo")) {
            p.setCod(Integer.parseInt(pesquisa.getText()));
            rsprodutosdao= objprodutosdao.filtrarCod(p);
            try{
                while(rsprodutosdao.next()) {
                    oblist.add(new Produtos(rsprodutosdao.getString("nome"), rsprodutosdao.getString("categoria"), rsprodutosdao.getInt("cod")));
                    tabela.setItems(oblist);
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        } else if(filtro.equals("Nome")) {
            p.setNome(pesquisa.getText());
            rsprodutosdao= objprodutosdao.filtrarNome(p);
            try{
                while(rsprodutosdao.next()) {
                    oblist.add(new Produtos(rsprodutosdao.getString("nome"), rsprodutosdao.getString("categoria"), rsprodutosdao.getInt("cod")));
                    tabela.setItems(oblist);
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        } else if(filtro.equals("Categoria")) {
            p.setCategoria(pesquisa.getText());
            rsprodutosdao= objprodutosdao.filtrarCategoria(p);
            try{
                while(rsprodutosdao.next()) {
                    oblist.add(new Produtos(rsprodutosdao.getString("nome"), rsprodutosdao.getString("categoria"), rsprodutosdao.getInt("cod")));
                    tabela.setItems(oblist);
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        }
    }

    public void carregaTabela()  {
        tabela.getItems().clear();
        tabelacod.setCellValueFactory(new PropertyValueFactory("cod"));
        tabelagrupo.setCellValueFactory(new PropertyValueFactory("categoria"));
        tabelanome.setCellValueFactory(new PropertyValueFactory("nome"));
        Produtos p = new Produtos();
        ProdutosDAO objprodutosdao = new ProdutosDAO();
        ResultSet rsprodutosdao = objprodutosdao.listarTodos(p);
        try {
            while (rsprodutosdao.next()) {
                oblist.add(new Produtos(rsprodutosdao.getString("nome"), rsprodutosdao.getString("categoria"), rsprodutosdao.getInt("cod")));
                tabela.setItems(oblist);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregaTabela();
        carregarCategorias();
    }
}
