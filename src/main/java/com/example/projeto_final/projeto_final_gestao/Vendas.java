package com.example.projeto_final.projeto_final_gestao;

public class Vendas {
    private int idVendas;
    private String quantidade;
    private Double total;

    public Vendas(int idVendas, String quantidade, Double total){
        this.idVendas = idVendas;
        this.quantidade = quantidade;
        this.total = total;
    }

    public Vendas(String quantidade, Double total) {
        this.quantidade = quantidade;
        this.total = total;
    }

    //Metodos getter
    public int getIdVendas(){
        return idVendas;
    }
    public String getQuantidade(){
        return quantidade;
    }
    public Double getTotal(){
        return total;
    }


    //Metodo setter
    public void setIdVendas(int idVendas){
        this.idVendas = idVendas;
    }
    public void setQuantidade(String quantidade){
        this.quantidade = quantidade;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
}
