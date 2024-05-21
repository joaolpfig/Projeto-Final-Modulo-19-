package com.example.projeto_final.projeto_final_gestao;

public class Produto {

    private int idProduto;
    private String nomeProduto;
    private double preco;
    private String tamanho;
    private String quantidade;

    public Produto(int idProduto, String nomeProduto, Double preco, String tamanho, String quantidade){
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
    }

    public Produto(String nomeProduto, Double preco, String tamanho, String quantidade) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
    }

    //Metodos getter
    public int getIdProduto(){
        return idProduto;
    }
    public String getNomeProduto(){
        return nomeProduto;
    }
    public Double getPreco(){
        return preco;
    }
    public String getTamanho(){
        return tamanho;
    }
    public String getQuantidade(){
        return quantidade;
    }


    //Metodo setter
    public void setIdProduto(int idProduto){
        this.idProduto = idProduto;
    }
    public void setNomeProduto(String nomeProduto){
        this.nomeProduto = nomeProduto;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    public void setQuantidade(String quantidade){
        this.quantidade = quantidade;
    }
}
