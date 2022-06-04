package Controller;

import Conexao.EstoqueDAO;
import Conexao.MovimentacaoDAO;
import com.example.demo.Categoria;
import com.example.demo.Estoque;
import com.example.demo.Movimentacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EstoqueController implements Initializable {
    @FXML
    private ComboBox<Categoria> filtro;
    @FXML
    private TextField pesquisa;
    private List<Categoria> categorias = new ArrayList<>();
    private ObservableList<Categoria> obsCategorias;

    @FXML
    private TableView<Estoque> tabela;
    @FXML
    private TableColumn<Estoque, Integer> tabelacod;
    @FXML
    private TableColumn<Estoque, Integer> tabelaqtd;
    @FXML
    private TableColumn<Estoque,String> tabelanome;

    ObservableList<Estoque> oblist  = FXCollections.observableArrayList();

    public void carregarCategorias(){
        Categoria categoria1 = new Categoria(1,"Codigo");
        Categoria categoria2 = new Categoria(2,"Nome");
        categorias.add(categoria1);
        categorias.add(categoria2);
        obsCategorias= FXCollections.observableArrayList(categorias);
        filtro.setItems(obsCategorias);
    }

    public String pegarCategoria(){
        Categoria categoria = filtro.getSelectionModel().getSelectedItem();
        return categoria.getNome();
    }

    public void filtrar() {
        tabela.getItems().clear();
        tabelacod.setCellValueFactory(new PropertyValueFactory("cod"));
        tabelaqtd.setCellValueFactory(new PropertyValueFactory("quantidade"));
        tabelanome.setCellValueFactory(new PropertyValueFactory("nome"));
        String filtro = pegarCategoria();
        Estoque e = new Estoque();
        EstoqueDAO objestoquedao = new EstoqueDAO();
        ResultSet rsestoquedao;
        if(filtro.equals("Codigo")) {
            e.setCod(Integer.parseInt(pesquisa.getText()));
            rsestoquedao= objestoquedao.filtrarCod(e);
            try{
                while(rsestoquedao.next()) {
                    oblist.add(new Estoque(rsestoquedao.getInt("cod"), rsestoquedao.getString("nome"), rsestoquedao.getInt("quantidade")));
                    tabela.setItems(oblist);
                }
            } catch (Exception er) {
                System.out.print(er);
            }
        } else if(filtro.equals("Nome")) {
            e.setNome(pesquisa.getText());
            rsestoquedao= objestoquedao.filtrarNome(e);
            try{
                while(rsestoquedao.next()) {
                    oblist.add(new Estoque(rsestoquedao.getInt("cod"), rsestoquedao.getString("nome"), rsestoquedao.getInt("quantidade")));
                    tabela.setItems(oblist);
                }
            } catch (Exception er) {
                System.out.print(er);
            }

        }

    }

    public void carregaTabela()  {
        tabela.getItems().clear();
        tabelacod.setCellValueFactory(new PropertyValueFactory("cod"));
        tabelaqtd.setCellValueFactory(new PropertyValueFactory("quantidade"));
        tabelanome.setCellValueFactory(new PropertyValueFactory("nome"));
        Estoque e = new Estoque();
        EstoqueDAO objestoquedao = new EstoqueDAO();
        ResultSet rsestoquedao = objestoquedao.listarTodos(e);
        try {
            while(rsestoquedao.next()) {
                oblist.add(new Estoque(rsestoquedao.getInt("cod"), rsestoquedao.getString("nome"), rsestoquedao.getInt("quantidade")));
                tabela.setItems(oblist);
            }
        }catch(Exception er){
            System.out.println(er);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregaTabela();
        carregarCategorias();

    }
}
