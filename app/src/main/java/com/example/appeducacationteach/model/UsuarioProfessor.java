package com.example.appeducacationteach.model;

public class UsuarioProfessor {


    private int id;
    private String idProfessor;
    private String nome;
    private String email;
    private String CPF;
    private  String senha;
    private String discplina;

    public UsuarioProfessor(String idProfessor, String nome, String email, String CPF, String senha, String discplina){
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.email = email;
        this.CPF = CPF;
        this.senha = senha;
        this.discplina = discplina;
    }

    public UsuarioProfessor(String nome, String nickname, String email, String senha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.CPF = cpf;
        this.idProfessor = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDiscplina() {
        return discplina;
    }

    public void setDiscplina(String discplina) {
        this.discplina = discplina;
    }
}
