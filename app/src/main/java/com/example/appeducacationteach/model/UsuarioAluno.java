package com.example.appeducacationteach.model;

public class UsuarioAluno {


    private int id;
    private String idAluno;
    private  String nome;
    private String nickname;
    private String email;
    private String senha;

    private int nivel;
    private int pontos;

    public UsuarioAluno(String nome, String nickname, String email, String senha){
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
        this.nivel = 1;
        this.pontos = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}
