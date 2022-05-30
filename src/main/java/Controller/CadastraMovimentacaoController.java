package Controller;
import javafx.scene.control.TextField;
import Conexao.MovimentacaoDAO;
import Conexao.ProdutosDAO;
import com.example.demo.Movimentacao;
import com.example.demo.Produtos;
import com.example.demo.RecuperaSenhaApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class CadastraMovimentacaoController implements Initializable{
    @FXML
    private TextField txtcodigo;
    @FXML
    private TextField txtvalor;
    @FXML
    private TextField txtquantidade;

    @FXML
    private ComboBox<Categoria> tipo;

    @FXML
    private TextField txtdata;
    private List<Categoria> categorias = new ArrayList<>();
    private ObservableList<Categoria> obsCategorias;

    public void carregarCategorias(){
        Categoria categoria1 = new Categoria(1,"Entrada");
        Categoria categoria2 = new Categoria(2,"Saida");
        categorias.add(categoria1);
        categorias.add(categoria2);
        obsCategorias=FXCollections.observableArrayList(categorias);
        tipo.setItems(obsCategorias);
    }


    public String pegarCategoria(){
            Categoria categoria = tipo.getSelectionModel().getSelectedItem();
            return categoria.getNome();
    }
    @FXML
    protected void voltarTela(ActionEvent actionEvent) {
        try {
            Node node = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(RecuperaSenhaApplication.class.getResource("MenuMovimentacoes.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 620, 400);
            stage.setTitle("Cadastro de movimentações");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarCategorias();
    }

    @FXML
    protected void cadastrarMovimentacao(ActionEvent actionEvent) {
        Produtos p = new Produtos();
        Movimentacao m = new Movimentacao();
        try {
            m.setCod(Integer.parseInt(txtcodigo.getText()));
            m.setQuantidade(Integer.parseInt(txtquantidade.getText()));
            m.setValor(Double.parseDouble(txtvalor.getText()));
            m.setTipo(pegarCategoria());
            m.setData(txtdata.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Você precisa preencher todos os campos");
            return;
        }
        //validaValor(m.getValor());
        MovimentacaoDAO objmovimentacaodao = new MovimentacaoDAO();
        ResultSet rsmovimentacaodao = objmovimentacaodao.verificaCod(m);
        try {
            if(rsmovimentacaodao.next()) {
                if(validaData(m.getData())){
                    objmovimentacaodao.cadastrarMovimentacao(m);
                    JOptionPane.showMessageDialog(null, "Movimentação realizada com sucesso");
                }
                txtcodigo.setText("");
                txtvalor.setText("");
                txtquantidade.setText("");
                txtdata.setText("");
            }
            else{
                JOptionPane.showMessageDialog(null, "Codigo inexistente");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean validaData(String data){
        int dia,mes,ano;
        String barra;
        barra=data.substring(2,3);
        if(!barra.equals("/")){
            JOptionPane.showMessageDialog(null,"Formato de data incorreto");
            return false;
        }
        barra=data.substring(5,6);
        if(!barra.equals("/")){
            JOptionPane.showMessageDialog(null,"Formato de data incorreto");
            return false;
        }
        try {
            dia = Integer.parseInt(data.substring(0, 2));
            mes = Integer.parseInt(data.substring(3, 5));
            ano = Integer.parseInt(data.substring(6, 10));
            System.out.println(dia+"/"+mes+"/"+ano);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Data inválida");
            return false;
        }
        if(mes<1||mes>12)
        {
            JOptionPane.showMessageDialog(null,"Mês inválido");
            return false;
        }
        if(mes%2!=0 && (dia<1 || dia>31)){
            JOptionPane.showMessageDialog(null,"Dia inválido");
            return false;
        }
        if((mes%2==0 && mes!=2) && (dia<1 || dia>30)){
            JOptionPane.showMessageDialog(null,"Dia inválido");
            return false;
        }
        if(mes==2 && (dia<1 || dia>28)){
            JOptionPane.showMessageDialog(null,"Dia inválido");
            return false;
        }
        return true;
    }

    /*public boolean validaValor(double valor){
        Movimentacao m = new Movimentacao();
        if(m.getTipo().equals("Saida")){
            MovimentacaoDAO objmovimentacaodao = new MovimentacaoDAO();
            ResultSet rsmovimentacaodao = objmovimentacaodao.retornaPreco(m);
            System.out.println(rsmovimentacaodao);
        }
        return true;
    }*/
}
