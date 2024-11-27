package com.example.alunoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        String login = (String) getIntent().getSerializableExtra("TextNome");
        setTitle("Dashboard");

    }

    public void CadastrarAlunos(View view){
        Intent intent = new Intent(this,CadastrarAlunoActivity.class);
        startActivity(intent);
    }

    public void ListarAlunos(View view){
        Intent intent = new Intent(this,ListarAlunoActivity.class);
        startActivity(intent);
    }


    public void FiltrarAlunosPorNota(View view) {
        Intent intent = new Intent(this, ListaAlunosPorNotaActivity.class);
        startActivity(intent);

    }


}