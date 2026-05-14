package com.example.appeducacationteach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appeducacationteach.database.DatabaseConection;
import com.example.appeducacationteach.model.UsuarioProfessor; // Importante!

public class UsuarioProfDAO {

    private DatabaseConection con;
    private SQLiteDatabase db;

    public UsuarioProfDAO(Context context) {
        con = new DatabaseConection(context);
    }

    public boolean inserir(UsuarioProfessor professor) {
        db = con.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("email", professor.getEmail());
        values.put("senha", professor.getSenha());
        values.put("nome", professor.getNome());
        values.put("CPF", professor.getCPF());

        long resultado = db.insert(DatabaseConection.TABELA_USUARIO_PROF, null, values);

        db.close();
        return resultado != -1;
    }

    public boolean login(String senha, String email) {
        db = con.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +DatabaseConection.TABELA_USUARIO_PROF +
                        " WHERE email = ? AND senha = ? AND nome = ? AND CPF = ?",
                new String[]{email, senha}

        );

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return existe;
    }
}