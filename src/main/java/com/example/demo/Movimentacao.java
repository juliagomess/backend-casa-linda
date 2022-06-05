package com.example.demo;

public class Movimentacao {

    private String tipo;
    private int quantidade;
    private int cod;
    private double valor;
    private String data;

    public Movimentacao(String tipo, int quantidade, int cod, String data) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.cod = cod;
        this.data = data;
    }
    public Movimentacao() {

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
