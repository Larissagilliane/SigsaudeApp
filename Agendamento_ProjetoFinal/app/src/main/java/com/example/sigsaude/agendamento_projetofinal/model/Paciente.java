package com.example.sigsaude.agendamento_projetofinal.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class Paciente implements Comparable<Paciente> {
    private String 	id; 	  /*< The identify of the Patiente */
    private String 	name;	  /*< The name of the Patiente */
    private String 	mother_name;	  /*< The name of the Patient's mother. */
    private String birth;   /*< The date of birth */
    private String 	cpf;	  /*< The cpf */
    private String 	ubs;	  /*< The number of ubs card */
    private String email; /*< The email */
    private String password; /*< The password */
    private String gender; /*< The gender */

    public Paciente() {
        this.id = "";
        this.name = "";
        this.mother_name = "";
        this.birth = null;
        this.cpf = "";
        this.ubs = "";
        this.email = "";
        this.password = "";
        this.gender = "";
    }

    public Paciente(String id, String name, String mother_name, String birth, String cpf, String ubs, String email, String password, String gender) {
        this.id = id;
        this.name = name;
        this.mother_name = mother_name;
        this.birth = birth;
        this.cpf = cpf;
        this.ubs = ubs;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUbs() {
        return ubs;
    }

    public void setUbs(String ubs) {
        this.ubs = ubs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.name + "  " + cpf;
    }

    @Override
    public int compareTo(@NonNull Paciente paciente) {
        if (this.getCpf() ==  paciente.getCpf() || this.getEmail() ==  paciente.getEmail())
            return 0;
        else return -1;
    }
}
