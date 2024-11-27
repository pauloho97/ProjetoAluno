package com.example.alunoapplication;

import static com.example.alunoapplication.CompartilharDados.lista;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RepositorioAluno extends SQLiteOpenHelper {
    public RepositorioAluno(@Nullable Context context) {
        super(context, "aluno", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE aluno (nome text, nota Real)";
        sqLiteDatabase.execSQL(sql);
        Log.i("aluno", "criado com sucesso a tabela aluno");

    }

    public void adicionarAluno(Aluno aluno){
        String sql = "insert into aluno (nome, nota) values('" + aluno.getNome() + "', " + aluno.getNota() + ")";
        Log.i("aluno", "SQL insert aluno: " + sql);
        super.getWritableDatabase().execSQL(sql);
    }

    public List<Aluno> listarAluno(){
        String sql = "SELECT * from aluno";
        Cursor cursor = getWritableDatabase().rawQuery(sql,null);
        cursor.moveToFirst();

        for(int i=0 ; i < cursor.getCount(); i++){
            // Obtém os dados do cursor
            String nome = cursor.getString(0);      // Primeiro campo (nome)
            Float idade = cursor.getFloat(1);       // Segundo campo (idade)

            // Cria um novo objeto Aluno usando o construtor
            Aluno aluno = new Aluno(nome, idade);

            // Adiciona o aluno à lista
            lista.add(aluno);
            cursor.moveToNext();
        }

        cursor.close();
        return lista;
    }

    public List<Aluno> listarAlunoPorNota(Float notaInicial, Float notaFinal){
        ArrayList<Aluno> listaFiltrada = new ArrayList<>();
        String sql = "SELECT * from aluno where nota >= " + notaInicial + " and nota <= " + notaFinal;
        Cursor cursor = getWritableDatabase().rawQuery(sql,null);
        cursor.moveToFirst();

        for(int i=0 ; i < cursor.getCount() ; i++){
            String nome = cursor.getString(0);      // Primeiro campo (nome)
            Float nota = cursor.getFloat(1);       // Segundo campo (nota)
            // Cria um novo objeto Aluno usando o construtor
            Aluno aluno = new Aluno(nome, nota);

            listaFiltrada.add(aluno);
            cursor.moveToNext();

        }

        cursor.close();
        return listaFiltrada;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}