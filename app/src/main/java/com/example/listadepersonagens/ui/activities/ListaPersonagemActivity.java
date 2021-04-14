package com.example.listadepersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listadepersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_de_personagem );
        setTitle( TITULO_APPBAR );
        configuraFabNovoPersonagem();
    }

    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById( R.id.floatingActionButton );
        botaoNovoPersonagem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioSalva();
            }
        } );
    }

    private void abreFormularioSalva() {
        startActivity( new Intent( ListaPersonagemActivity.this, FormularioPersonagemActivity.class ) );
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listadePersonagens = findViewById( R.id.activity_main_lista_personagem );
        final List<Personagem> personagens = dao.todos();
        listaDePersonagens( listadePersonagens, personagens );
        configuraItemPorClique( listadePersonagens );
    }

    private void configuraItemPorClique(ListView listadePersonagens) {
        listadePersonagens.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition( posicao );
                abreFormularioModoEditar( personagemEscolhido );
            }
        });
    }

    private void abreFormularioModoEditar(Personagem personagem) {
        Intent vaiParaFormulario = new Intent( ListaPersonagemActivity.this, FormularioPersonagemActivity.class );
        vaiParaFormulario.putExtra( CHAVE_PERSONAGEM, personagem );
        startActivity( vaiParaFormulario );
    }

    private void listaDePersonagens(ListView listadePersonagens, List<Personagem> personagens) {
        listadePersonagens.setAdapter( new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, personagens ) );
    }
}

