package com.example.sigsaude.agendamento_projetofinal.model;

import com.example.sigsaude.agendamento_projetofinal.utils.Unidades;

public class Profissional {
    private String nome;
    private String especialidade;
    private String unidades;
    private String id;

    public Profissional() {
        this.id ="";
        this.nome = "";
        this.especialidade = "";
        this.unidades = "";
    }

    public Profissional(String nome, String especialidade, String unidades, String id) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.unidades = unidades;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return nome + " - "  + especialidade;
    }
}
