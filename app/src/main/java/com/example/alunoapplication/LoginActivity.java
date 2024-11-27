package com.example.alunoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

    }


    public void logar(View view){
        EditText TextNome = findViewById(R.id.editTextNome);
        EditText TextSenha = findViewById(R.id.editTextSenha);

        String nome = TextNome.getText().toString().trim();
        String senha = TextSenha.getText().toString().trim();

        //campos vazios
        if(nome.equals("") || senha.equals("")){
            Toast.makeText(this, R.string.n_o_pode_ter_campos_vazios, Toast.LENGTH_SHORT).show();

            TextNome.setText("");
            TextSenha.setText("");
            return;
        }

        List<String> listLogin = Arrays.asList("admin","jumanji","paulo");

        if(listLogin.contains(nome) && senha.equals("123")){
            Toast.makeText(this, R.string.logado_com_sucesso, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DashboardActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("TextNome",nome);
            intent.putExtras(bundle);
            startActivity(intent);

            return;
        } else {
            Toast.makeText(this, R.string.loginInvalido, Toast.LENGTH_SHORT).show();
            TextNome.setText("");
            TextSenha.setText("");
            return;
        }
    }
}