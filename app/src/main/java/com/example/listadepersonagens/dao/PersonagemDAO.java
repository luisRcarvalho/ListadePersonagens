package com.example.listadepersonagens.dao;

import com.example.listadepersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
//classe onde se salva,edita e organiza os novos personagens inseridos

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1;
//metodo onde se adiciona e salva os peronsagens novos criados
    public void salva(Personagem personagemSalvo) {
        personagemSalvo.setId( contadorDeId );
        personagens.add( personagemSalvo );
        contadorDeId++;
    }
//metodo onde se faz a edição dos personagens ja criados
    public void edita(Personagem personagem) {
        Personagem personagemEscolhido = null;
        for (Personagem p :
                personagens) {
            if (p.getId() == personagem.getId()) {
                personagemEscolhido = p;
            }
        }
        if (personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf( personagemEscolhido );
            personagens.set( posicaoDoPersonagem, personagem );
        }
    }

    public List<Personagem> todos() {
        return new ArrayList<>( personagens );
    }
}
