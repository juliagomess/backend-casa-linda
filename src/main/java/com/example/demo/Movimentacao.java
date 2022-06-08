package com.example.demo;

public class Movimentacao {

    private String tipo;
    private int quantidade;
    private int cod;
    private double valor;

    private String fornecedor;

    private String nome;
    private String data;

    public Movimentacao(String tipo, int quantidade, int cod, String data) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.cod = cod;
        this.data = data;
    }
    public Movimentacao() {

    }



    public Movimentacao(String tipo, int quantidade, int cod, double valor, String data, String fornecedor, String nome) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.cod = cod;
        this.valor = valor;
        this.data = data;
        this.fornecedor = fornecedor;
        this.nome = nome;

    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
