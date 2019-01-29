package com.example.sigsaude.agendamento_projetofinal.model;

import java.io.Serializable;

public class Horario implements Serializable {
    private String data;
    private String hora;
    private Boolean disponivel;
    private Profissional profissional;
    private String id;

    public Horario(String data, String hora, Boolean disponivel, Profissional profissional, String id) {
        this.data = data;
        this.hora = hora;
        this.disponivel = disponivel;
        this.profissional = profissional;
        this.id = id;
    }

    public Horario() {
        this.data = null;
        this.hora = null;
        this.disponivel = true;
        this.profissional = null;
        this.id = "";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Horario: " + data + ':' + hora +
                " - " + disponivel;
    }
}
