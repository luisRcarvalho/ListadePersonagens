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

//variaveis dos nomes das paginas
    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem" ;
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    //variavies dos campos preenchiveis do app
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    //varivel de criação de refenrecia ao personagem e personagemDao(Save)
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;
//quando inicializa o app, faz os ajustes de layout e inicializa os metodos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario_personagem );
        inicializaCampos();
        configuraBotaoSalvar();
        carregaPersonagem();
    }
//metodo que carrega os personagens ja criados
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
//metodo que permite que os campos sejam preenchidos
    private void preencheCampos() {
        campoNome.setText( personagem.getNome() );
        campoAltura.setText( personagem.getAltura() );
        campoNascimento.setText( personagem.getNascimento() );
    }
//metodo que realiza o save das informações
    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById( R.id.button_salvar );
        botaoSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            //chama o metodo de finalização ao apertar o botão
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }
//metodo onde se finaliza o preenchimento do formulario
    private void finalizaFormulario() {
        preenchePersonagem();
        //caso o personagem ja exista, utiliza o metodo de edição criado no dao
        if (personagem.IdValido()) {
            dao.edita( personagem );
            //e se nao existe, utiliza o metodo de salvar criado no dao para fazer um novo personagem
        } else {
            dao.salva( personagem );
        }
        finish();
    }
//metodo que referencia as variveis dos campos, ao campos criados no layout
    private void inicializaCampos() {
        campoNome = findViewById( R.id.editText_nome );
        campoAltura = findViewById( R.id.editText_altura );
        campoNascimento = findViewById( R.id.editText_nascimento );
    }
//metodo que preenche as variaveis com as informações escritas nos campos
    private void preenchePersonagem() {
        //conversão dos textos escritos no campo para string
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();
//seta as informações
        personagem.setNome( nome );
        personagem.setAltura( altura );
        personagem.setNascimento( nascimento );
    }
}