package com.example.listadepersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    //variaveis das informações que contem no personagem
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;
//classe de personagem utilizando as variaveis criadas como parametro
    public Personagem(String nome, String altura, String nascimento) {
        //referenciando o conteudo das variaveis
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }
// classe personagem vazia utilizada no formulario
    public Personagem() {
    }
//gets e sets
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean IdValido() {
        return id > 0;
    }
}

