package com.example.appeducacationteach.model;

public class Atividade {

    private int id;
    private  String titulo;
    private String enunciado;
    private String dificuldade;
    private String exemploentrada;
    private String exemplosaida;
    private String dataEntrega;

    public Atividade(String titulo, String enunciado, String dificuldade, String exemploentrada, String exemplosaida, String dataEntrega){
        this.titulo = titulo;
        this.enunciado = enunciado;
        this.dificuldade = dificuldade;
        this.exemploentrada = exemploentrada;
        this.exemplosaida = exemplosaida;
        this.dataEntrega = dataEntrega;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getExemploentrada() {
        return exemploentrada;
    }

    public void setExemploentrada(String exemploentrada) {
        this.exemploentrada = exemploentrada;
    }

    public String getExemplosaida() {
        return exemplosaida;
    }

    public void setExemplosaida(String exemplosaida) {
        this.exemplosaida = exemplosaida;
    }
}
