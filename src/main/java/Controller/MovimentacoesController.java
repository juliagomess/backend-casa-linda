package Controller;

import Conexao.MovimentacaoDAO;
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

public class MovimentacoesController implements Initializable {

    @FXML
    private ComboBox<Categoria> filtro;
    @FXML
    private TextField pesquisa;
    private List<Categoria> categorias = new ArrayList<>();
    private ObservableList<Categoria> obsCategorias;

    @FXML
    private TableView<Movimentacao> tabela;
    @FXML
    private TableColumn<Movimentacao, Integer> tabelacod;
    @FXML
    private TableColumn<Movimentacao,String> tabeladata;
    @FXML
    private TableColumn<Movimentacao, Integer> tabelaqtd;
    @FXML
    private TableColumn<Movimentacao,String> tabelatipo;

    public void iniciarTabela(){
        tabelacod.setCellValueFactory(new PropertyValueFactory("cod"));
        tabelatipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tabelaqtd.setCellValueFactory(new PropertyValueFactory("quantidade"));
        tabeladata.setCellValueFactory(new PropertyValueFactory("data"));
    }
    public ObservableList<Movimentacao> atualizaTabela(){

    }
    public void carregarCategorias(){
        Categoria categoria1 = new Categoria(1,"Codigo");
        Categoria categoria2 = new Categoria(2,"Data");
        Categoria categoria3 = new Categoria(2,"Tipo");
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

    public void filtrar() {
        String filtro = pegarCategoria();
        Movimentacao m = new Movimentacao();

        MovimentacaoDAO objmovimentacaodao = new MovimentacaoDAO();
        ResultSet rsmovimentacaodao = objmovimentacaodao.verificaCod(m);
        if(filtro.equals("Codigo")) {
            m.setCod(Integer.parseInt(pesquisa.getText()));
            rsmovimentacaodao= objmovimentacaodao.filtrarCod(m);
            try{
                if(rsmovimentacaodao.next()) {
                    System.out.printf("Achou codigo");
                    System.out.print(rsmovimentacaodao.getInt(2));
                    //tabela.setItems();

                } else {
                    System.out.printf("nao achou codigo");
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        } else if(filtro.equals("Data")) {
            m.setData(pesquisa.getText());
            CadastraMovimentacaoController cm = new CadastraMovimentacaoController();

            if(!cm.validaData(m.getData())) {
                System.out.print("Data invalida");
                return;
            }

            rsmovimentacaodao= objmovimentacaodao.filtrarData(m);
            try{
                if(rsmovimentacaodao.next()) {
                    System.out.printf("Achou data");
                } else {
                    System.out.printf("nao achou data");
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        } else if(filtro.equals("Tipo")) {
            m.setTipo(pesquisa.getText());
            rsmovimentacaodao= objmovimentacaodao.filtrarTipo(m);
            try{
                if(rsmovimentacaodao.next()) {
                    System.out.printf("Achou tipo");
                } else {
                    System.out.printf("nao achou tipo");
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
