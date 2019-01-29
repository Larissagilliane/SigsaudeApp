package com.example.sigsaude.agendamento_projetofinal.model;

public class Administrador {
    private String nome;
    private String cpf;
    private String senha;
    private String id;

    public Administrador() {
        this.id = "";
        this.cpf = "";
        this.nome = "";
        this.senha = "";
    }


    public Administrador(String id, String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
