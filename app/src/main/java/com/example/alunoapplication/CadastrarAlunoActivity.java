package com.example.alunoapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastrarAlunoActivity extends AppCompatActivity {
    RepositorioAluno repositorioAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_aluno);

        setTitle("Cadastrar");

        // Inicializando o repositório
        repositorioAluno = new RepositorioAluno(this);
    }

    public void cadastrarOAluno(View view){
        EditText textoNome = findViewById(R.id.editTextNomeAluno);
        EditText textoNota = findViewById(R.id.editTextNota);

        String nomeAluno = textoNome.getText().toString().trim();
        Float notaAluno;



        if(nomeAluno.isEmpty() || textoNota.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.erroCamposVazios, Toast.LENGTH_SHORT).show();
            return;
        }

        // se nome aluno nã for no padrão regex de texto, será inválido
        if(!validarTexto(nomeAluno)){
            Toast.makeText(this, "Nome não pode conter números", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validação de notaAluno
        String notaString = textoNota.getText().toString().trim();

        // Verifica se a nota é um número válido antes de tentar convertê-la
        if (!isNumeric(notaString)) {
            Toast.makeText(this, "A nota deve ser um número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        //tratamento correto da notaAluno, sendo Float e dentro de 0 a 10.
        try{
            notaAluno = Float.parseFloat(textoNota.getText().toString().trim());

            if (notaAluno < 0 || notaAluno > 10){
                Toast.makeText(this, "nota não pode ser negativa ou acima de 10", Toast.LENGTH_SHORT).show();
                return;
            }

            Aluno aluno = new Aluno(nomeAluno,notaAluno);
            repositorioAluno.adicionarAluno(aluno);
            Toast.makeText(this, R.string.aluno_cadastrado_com_sucesso, Toast.LENGTH_SHORT).show();

            textoNome.setText("");
            textoNota.setText("");
            return;

        } catch(Exception e){
            Log.e("Aluno", "Erro ao processar nota: " + e.getMessage());
            Toast.makeText(this, R.string.numerosReais, Toast.LENGTH_SHORT).show();
        }



        textoNome.setText("");
        textoNota.setText("");

    }

    // Método para validar apenas letras e caracteres especiais, sem números
    public static boolean validarTexto(String texto) {
        // Regex para letras e caracteres especiais, sem números
        String regex = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s\\p{Punct}]+$";

        // Compilar o padrão
        Pattern pattern = Pattern.compile(regex);

        // Criar o matcher (comparador)
        Matcher matcher = pattern.matcher(texto);

        // Retornar se o texto corresponde ao padrão
        return matcher.matches();
    }

    // Método para verificar se a string é um número válido
    public static boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}