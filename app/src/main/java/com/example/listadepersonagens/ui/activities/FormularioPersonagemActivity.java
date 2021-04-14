package com.example.listadepersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;

import static com.example.listadepersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {


    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem" ;
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario_personagem );
        inicializaCampos();
        configuraBotaoSalvar();
        carregaPersonagem();
    }

    private void carregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra( CHAVE_PERSONAGEM )) {
            setTitle( TITULO_APPBAR_EDITA_PERSONAGEM );
            personagem = (Personagem) dados.getSerializableExtra( CHAVE_PERSONAGEM );
            preencheCampos();
        } else {
            setTitle( TITULO_APPBAR_NOVO_PERSONAGEM );
            personagem = new Personagem();
        }
    }

    private void preencheCampos() {
        campoNome.setText( personagem.getNome() );
        campoAltura.setText( personagem.getAltura() );
        campoNascimento.setText( personagem.getNascimento() );
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById( R.id.button_salvar );
        botaoSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preenchePersonagem();
        if (personagem.IdValido()) {
            dao.edita( personagem );
        } else {
            dao.salva( personagem );
        }
        finish();
    }

    private void inicializaCampos() {
        campoNome = findViewById( R.id.editText_nome );
        campoAltura = findViewById( R.id.editText_altura );
        campoNascimento = findViewById( R.id.editText_nascimento );
    }

    private void preenchePersonagem() {
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        personagem.setNome( nome );
        personagem.setAltura( altura );
        personagem.setNascimento( nascimento );
    }
}