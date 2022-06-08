package com.example.demo;
import Controller.DetalhesProdutoController;
import Controller.ListaProdutosController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Produtos {
    String nome;
    String fornecedor;
    String categoria;
    String descricao;
    int cod;
    double valor_venda;

    Button botao;
    public Produtos() {

    }

    public Button getBotao() {
        return botao;
    }

    public void setBotao(Button botao) {
        this.botao = botao;
    }


    public Produtos(String nome, String categoria, int cod, Button botao) {
        DetalhesProdutoController d = new DetalhesProdutoController();
        this.nome = nome;
        this.categoria = categoria;
        this.cod = cod;
        this.botao = botao;
        botao.setOnAction((ActionEvent event) -> {
            try {
                 Node node = (Node) event.getSource();
                 Stage currentStage = (Stage) node.getScene().getWindow();
                currentStage.close();
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("DetalhesProduto.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 620, 400);
                stage.setTitle("Detalhes de produto");
                stage.setScene(scene);
                notifyAllListeners(this.getCod());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //botao.setOnAction();
    }

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen {
        void OnScreenChanged(int cod);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListeners(int cod) {
        for(OnChangeScreen l : listeners) {
            l.OnScreenChanged(cod);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }
}
