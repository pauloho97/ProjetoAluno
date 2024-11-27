package com.example.alunoapplication;

public class Aluno {

    private String nome;
    private Float nota;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public Float getNota() {
        return nota;
    }

    public Aluno(String nome, Float nota) {
        this.nome = nome;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Aluno: " +
                "nome = " + nome +
                ", nota = " + nota;
    }
}
