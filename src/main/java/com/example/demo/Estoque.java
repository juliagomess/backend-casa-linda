package com.example.demo;

public class Estoque {
    private int cod;
    private String nome;
    private int quantidade;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Estoque(int cod, String nome, int quantidade) {
        this.cod = cod;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Estoque() {
    }
}
