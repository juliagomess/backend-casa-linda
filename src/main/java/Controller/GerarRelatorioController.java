package Controller;

import Conexao.MovimentacaoDAO;
import Conexao.ProdutosDAO;
import com.example.demo.Categoria;
import com.example.demo.LoginApplication;
import com.example.demo.Movimentacao;
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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GerarRelatorioController implements Initializable {
    @FXML
    private ComboBox<Categoria> filtro;
    @FXML
    private TextField pesquisa;
    private List<Categoria> categorias = new ArrayList<>();
    private ObservableList<Categoria> obsCategorias;

    @FXML
    private TableView<Movimentacao> tabela;
    @FXML
    private TableColumn<Movimentacao, Integer> tabelacodigo;
    @FXML
    private TableColumn<Produtos,String> tabelanome;
    @FXML
    private TableColumn<Produtos, String> tabelafornecedor;
    @FXML
    private TableColumn<Movimentacao, String> tabeladata;
    @FXML
    private TableColumn<Movimentacao, String> tabelatipo;
    @FXML
    private TableColumn<Movimentacao, Integer> tabelaquantidade;
    @FXML
    private TableColumn<Movimentacao, Integer> tabelavalor;

    ObservableList<Movimentacao> oblist  = FXCollections.observableArrayList();
    public void carregarCategorias(){
        Categoria categoria1 = new Categoria(1,"Codigo");
        Categoria categoria2 = new Categoria(2,"Nome");
        Categoria categoria3 = new Categoria(3,"Fornecedor");
        Categoria categoria4 = new Categoria(4,"Data");
        Categoria categoria5 = new Categoria(5,"Tipo");
        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);
        categorias.add(categoria5);
        obsCategorias= FXCollections.observableArrayList(categorias);
        filtro.setItems(obsCategorias);
    }
    public String pegarCategoria(){
        Categoria categoria = filtro.getSelectionModel().getSelectedItem();
        return categoria.getNome();
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


    public void filtrar() {
        tabela.getItems().clear();
        tabelacodigo.setCellValueFactory(new PropertyValueFactory("cod"));
        tabelanome.setCellValueFactory(new PropertyValueFactory("nome"));
        tabelafornecedor.setCellValueFactory(new PropertyValueFactory("fornecedor"));
        tabeladata.setCellValueFactory(new PropertyValueFactory("data"));
        tabelatipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tabelavalor.setCellValueFactory(new PropertyValueFactory("valor"));
        tabelaquantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        String filtro = pegarCategoria();
        Produtos p = new Produtos();
        Movimentacao m = new Movimentacao();
        MovimentacaoDAO objmovimentacaodao =  new MovimentacaoDAO();
        ProdutosDAO objprodutosdao = new ProdutosDAO();
        ResultSet rsmovimentacaodao;
        ResultSet rsprodutosdao;
        if(filtro.equals("Codigo")) {
            m.setCod(Integer.parseInt(pesquisa.getText()));
            rsmovimentacaodao= objmovimentacaodao.filtrarCod(m);
            try{
                 while(rsmovimentacaodao.next()) {
                     p.setCod(rsmovimentacaodao.getInt("cod"));
                     rsprodutosdao =  objprodutosdao.filtrarCod(p);
                     if(rsprodutosdao.next()) {
                        oblist.add(new Movimentacao(rsmovimentacaodao.getString("tipo"), rsmovimentacaodao.getInt("quantidade"), rsmovimentacaodao.getInt("cod"), rsmovimentacaodao.getDouble("valor"), rsmovimentacaodao.getString("data"), rsprodutosdao.getString("fornecedor"), rsprodutosdao.getString("nome")));
                        tabela.setItems(oblist);
                    }
                }

            } catch (Exception e) {
                System.out.print(e);
            }
        } else if(filtro.equals("Nome")) {
            p.setNome(pesquisa.getText());
            rsmovimentacaodao = objmovimentacaodao.filtrarNome(p);
            try{
                while(rsmovimentacaodao.next()) {
                    p.setCod(rsmovimentacaodao.getInt("cod"));
                    rsprodutosdao =  objprodutosdao.filtrarCod(p);
                    if(rsprodutosdao.next()) {
                        oblist.add(new Movimentacao(rsmovimentacaodao.getString("tipo"), rsmovimentacaodao.getInt("quantidade"), rsmovimentacaodao.getInt("cod"), rsmovimentacaodao.getDouble("valor"), rsmovimentacaodao.getString("data"), rsprodutosdao.getString("fornecedor"), rsprodutosdao.getString("nome")));
                        tabela.setItems(oblist);
                    }
                }

            } catch (Exception e) {
                System.out.print(e);
            }
        } else if(filtro.equals("Fornecedor")) {
            p.setFornecedor(pesquisa.getText());
            rsmovimentacaodao = objmovimentacaodao.filtrarFonecedor(p);
            try{
                while(rsmovimentacaodao.next()) {
                    p.setCod(rsmovimentacaodao.getInt("cod"));
                    rsprodutosdao =  objprodutosdao.filtrarCod(p);
                    if(rsprodutosdao.next()){
                        oblist.add(new Movimentacao(rsmovimentacaodao.getString("tipo"), rsmovimentacaodao.getInt("quantidade"), rsmovimentacaodao.getInt("cod"), rsmovimentacaodao.getDouble("valor"), rsmovimentacaodao.getString("data"), rsprodutosdao.getString("fornecedor"), rsprodutosdao.getString("nome")));
                        tabela.setItems(oblist);
                    }
                }

            } catch (Exception e) {
                System.out.print(e);
            }
        } else if(filtro.equals("Data")) {
            
            m.setData(pesquisa.getText());
            rsmovimentacaodao= objmovimentacaodao.filtrarData(m);
            try{
                while(rsmovimentacaodao.next()) {
                    p.setCod(rsmovimentacaodao.getInt("cod"));
                    rsprodutosdao =  objprodutosdao.filtrarCod(p);
                    if(rsprodutosdao.next()) {
                        oblist.add(new Movimentacao(rsmovimentacaodao.getString("tipo"), rsmovimentacaodao.getInt("quantidade"), rsmovimentacaodao.getInt("cod"), rsmovimentacaodao.getDouble("valor"), rsmovimentacaodao.getString("data"), rsprodutosdao.getString("fornecedor"), rsprodutosdao.getString("nome")));
                        tabela.setItems(oblist);
                    }
                }

            } catch (Exception e) {
                System.out.print(e);
            }
        } else if(filtro.equals("Tipo")) {
            m.setTipo(pesquisa.getText());
            rsmovimentacaodao= objmovimentacaodao.filtrarTipo(m);
            try{
                while(rsmovimentacaodao.next()) {
                    p.setCod(rsmovimentacaodao.getInt("cod"));
                    rsprodutosdao =  objprodutosdao.filtrarCod(p);
                    if(rsprodutosdao.next()) {
                        oblist.add(new Movimentacao(rsmovimentacaodao.getString("tipo"), rsmovimentacaodao.getInt("quantidade"), rsmovimentacaodao.getInt("cod"), rsmovimentacaodao.getDouble("valor"), rsmovimentacaodao.getString("data"), rsprodutosdao.getString("fornecedor"), rsprodutosdao.getString("nome")));
                        tabela.setItems(oblist);
                    }
                }

            } catch (Exception e) {
                System.out.print(e);
            }
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarCategorias();
    }
}
