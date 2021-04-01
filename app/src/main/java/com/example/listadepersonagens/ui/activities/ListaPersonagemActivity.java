package com.example.listadepersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_de_personagem );
        setTitle( "Lista de Personagens" );

        FloatingActionButton botaoNovoPersonagem = findViewById( R.id.floatingActionButton );
        botaoNovoPersonagem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class) );
            }
        } );

        //List<String> personagem = new ArrayList<>( Arrays.asList("Alex", "Ken", "Ryu", "Chun Li"));


   /*     TextView primeiroPersonagem = findViewById( R.id.textView );
        TextView segundoPersonagem = findViewById( R.id.textView2 );
        TextView terceiroPersonagem = findViewById( R.id.textView3 );

        primeiroPersonagem.setText( personagem.get(0));
        segundoPersonagem.setText( personagem.get(1));
        terceiroPersonagem.setText( personagem.get(2));*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        PersonagemDAO dao = new PersonagemDAO();

        ListView listadePersonagens = findViewById( R.id.activity_main_lista_personagem );
        List<Personagem> personagens = dao.todos();
        listadePersonagens.setAdapter( new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, personagens ) );
        listadePersonagens.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagens.get(posicao);
                Log.i("posicao", "" + personagemEscolhido );
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                startActivity( vaiParaFormulario );
            }
        } );
    }
}

