package com.example.alunoapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosPorNotaActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private Float notaInicial;
    private Float notaFinal;
    private RepositorioAluno repositorioAluno;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos_por_nota);

        editText1 = findViewById(R.id.editTextNotaInicial);
        editText2 = findViewById(R.id.editTextNotaFinal);


    }

    public void FiltroNotas(View view) {

        if(editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()){
            Toast.makeText(this, "Nenhum dos campos pode ser vazios", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            notaInicial = Float.parseFloat(editText1.getText().toString());
            notaFinal = Float.parseFloat(editText2.getText().toString());


            if (notaInicial > notaFinal) {
                Toast.makeText(this, "A nota inicial não pode ser maior que a nota final", Toast.LENGTH_SHORT).show();
                return;
            }

            if (notaInicial < 0 || notaInicial > 10 || notaFinal < 0 || notaFinal > 10){
                Toast.makeText(this, "nota não pode ser negativa ou acima de 10", Toast.LENGTH_SHORT).show();
                return;
            }


        } catch (NumberFormatException e){
            Toast.makeText(this, "Digite apenas números", Toast.LENGTH_SHORT).show();
            return;
        }

        //chamando método da listagem dos alunos
        ListagemDosAlunos();

    }

    public void ListagemDosAlunos(){
        //captando valores pelos id

        listView = findViewById(R.id.listListaFiltrada);

        //inicializa o repositório
        repositorioAluno = new RepositorioAluno(this);


        List<Aluno> listaBD = repositorioAluno.listarAlunoPorNota(notaInicial, notaFinal);

        if(listaBD.isEmpty() ){
            Toast.makeText(this, "Nenhum aluno encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        String [] dados = new String[listaBD.size()];
        for ( int i = 0; i < listaBD.size(); i++){
            Aluno aluno = listaBD.get(i);
            dados[i] = listaBD.get(i).toString();

        }


        ArrayAdapter<Aluno> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaBD);
        listView.setAdapter(arrayAdapter);

    }
}