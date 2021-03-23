package com.example.listadepersonagens.dao;

import com.example.listadepersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private  final  static List<Personagem> personagens = new ArrayList<>();

    public void salva(Personagem personagemSalvo) {

        personagens.add(personagemSalvo);
    }

    public List<Personagem> todos() {

        return  new ArrayList<>(personagens);
    }
}
