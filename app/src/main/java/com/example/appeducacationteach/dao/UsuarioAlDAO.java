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

        long resultando = db.insert(DatabaseConection.TABELA_USUARIO_ALU,null, values)

        return resultando != 1;
    }

    public boolean login(String email, String senha){
        db = con.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM" +DatabaseConection.TABELA_USUARIO_ALU +
                        "WHERE email=? AND senha=?",
                new String[]{email, senha}
        );

        boolean existe = cursor.getCount() > 0:
        cursor.close();

        return existe;
    }

}
