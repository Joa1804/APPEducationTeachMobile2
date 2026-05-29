package com.example.appeducacationteach.dao;

import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.util.Log;

import com.example.appeducacationteach.database.DatabaseConection;
import com.example.appeducacationteach.model.UsuarioAluno;

public class UsuarioAlDAO {

    private SQLiteDatabase db;
    private DatabaseConection con;

    public UsuarioAlDAO(Context context){
        con = new DatabaseConection(context);
    }

    public boolean inserir(UsuarioAluno usuario){
        db = con.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        values.put("nome", usuario.getNome());
        values.put("nickname",usuario.getNickname());
        values.put("nivel",usuario.getNivel());
        values.put("pontos", usuario.getPontos());

        long resultando = db.insert(DatabaseConection.TABELA_USUARIO_ALU,null, values);

        db.close();
        return resultando != -1;
    }

    public boolean login(String email, String senha){
        db = con.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +DatabaseConection.TABELA_USUARIO_ALU +
                        " WHERE email = ? AND senha=?",
                new String[]{email, senha }
        );

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return existe;
    }


    public UsuarioAluno buscarPorEmail(String email) {
        db = con.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseConection.TABELA_USUARIO_ALU +
                        " WHERE email = ?",
                new String[]{email}
        );

        if (cursor.moveToFirst()) {
            UsuarioAluno aluno = new UsuarioAluno(
                    cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nickname")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("senha"))
            );
            aluno.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            aluno.setNivel(cursor.getInt(cursor.getColumnIndexOrThrow("Nivel")));
            aluno.setPontos(cursor.getInt(cursor.getColumnIndexOrThrow("Pontos")));
            cursor.close();

            return aluno;
        }

        cursor.close();
        db.close();
        return  null;

    }

}


