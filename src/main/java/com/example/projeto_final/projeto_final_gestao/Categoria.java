package com.example.projeto_final.projeto_final_gestao;

public class Categoria {
    private int idCategoria;
    private String categoria;
    private String cor;

    public Categoria(int idCategoria, String categoria, String cor){
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.cor = cor;
    }

    public Categoria(String categoria, String cor) {
        this.categoria = categoria;
        this.cor = cor;
    }

    //Metodos getter
    public int getIdCategoria(){
        return idCategoria;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getCor(){
        return cor;
    }


    //Metodo setter
    public void setIdCategoria(int idCategoria){
        this.idCategoria = idCategoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

}
