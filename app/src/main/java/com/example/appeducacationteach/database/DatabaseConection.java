package com.example.appeducacationteach.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseConection extends SQLiteOpenHelper{

    private static final String DB_NAME = "app.db";
    private static final int DB_VERSION = 6;

    public static final String TABELA_USUARIO_ALU = "UsuarioAluno";
    public static final String TABELA_USUARIO_PROF = "UsuarioProfessor";

    public DatabaseConection(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlProf = "CREATE TABLE " + TABELA_USUARIO_PROF + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT UNIQUE," +
                "senha TEXT, " +
                "CPF TEXT, " +
                "nome TEXT," +
                "disciplina TEXT)";


        String sqlAluno = "CREATE TABLE " + TABELA_USUARIO_ALU + "("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT UNIQUE," +
                "senha TEXT, " +
                "nome TEXT," +
                "nickname TEXT,"+
                "nivel INTEGER DEFAULT 1," +
                "pontos INTEGER DEFAULT 0)";

        db.execSQL(sqlAluno);
        db.execSQL(sqlProf);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO_ALU);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO_PROF);
        onCreate(db);
    }

}
