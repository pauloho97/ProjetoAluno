package com.example.alunoapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListarAlunoActivity extends AppCompatActivity {
    private ListView listaView;
    private RepositorioAluno repositorioAluno;
    private TextView medNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_aluno);

        setTitle("Lista dos Alunos e Notas");

        atualizarLista();

    }

    private void atualizarLista(){
        medNota = findViewById(R.id.TextMedNota);

        listaView = findViewById(R.id.ListAlunos);

        RepositorioAluno repositorioAluno = new RepositorioAluno(this);

        List<Aluno> listaBD = repositorioAluno.listarAluno();

        String [] dados = new String[listaBD.size()];
        for (int i = 0; i < listaBD.size(); i++){
            Aluno aluno = listaBD.get(i);
            dados[i] = listaBD.get(i).toString();
        }

        // vai ser a nota + o valor do somador
        float somador = 0;
        for (Aluno aluno : listaBD){
            somador += aluno.getNota();
        }

        //fórmula para média
        float media = somador/listaBD.size();

        //variável = String.format para formatar a media nesse formato: 8.47
        String mediaFormatada = String.format(Locale.US, "%.2f", media);

        ArrayAdapter<Aluno> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaBD);
        listaView.setAdapter(arrayAdapter);

        medNota.setText("Total media das notas = " + mediaFormatada);
    }
}