package com.example.listadepersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario_personagem );
        setTitle( "Formulario de Personagens" );

        campoNome = findViewById( R.id.editText_nome );
        campoAltura = findViewById( R.id.editText_altura);
        campoNascimento = findViewById( R.id.editText_nascimento);


        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);
                finish();

                //startActivity( new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class) );

             /*   Toast.makeText( FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - " + personagemSalvo.getAltura()+ " - "+ personagemSalvo.getNascimento(),Toast.LENGTH_SHORT).show();*/

                //new Personagem(nome,altura,nascimento);
                personagemSalvo.setNome( nome );
                personagemSalvo.setAltura( altura );
                personagemSalvo.setNascimento( nascimento );
                dao.edita( personagemSalvo );

                //Toast.makeText( FormularioPersonagemActivity.this, "Estou Funcionando!", Toast.LENGTH_SHORT ).show();
            }
        });

        Intent dados = getIntent();
        Personagem personagem = (Personagem) dados.getSerializableExtra( "personagem" );
        campoNome.setText( personagem.getNome() );
        campoAltura.setText( personagem.getAltura() );
        campoNascimento.setText( personagem.getNascimento() );
    }
}