package com.example.sigsaude.agendamento_projetofinal.model;

public class Agendamento {
    private Paciente paciente;
    private Horario disponivel;
    private String id;

    public Agendamento(String id, Paciente paciente, Horario disponivel) {
        this.paciente = paciente;
        this.disponivel = disponivel;
        this.id = id;
    }

    public Agendamento() {
        this.paciente = null;
        this.disponivel = null;
        this.id = "";
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Horario getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Horario disponivel) {
        this.disponivel = disponivel;
    }
}
