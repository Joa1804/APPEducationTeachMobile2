package com.example.appeducacationteach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appeducacationteach.database.DatabaseConection;
import com.example.appeducacationteach.model.Atividade;

import java.util.ArrayList;
import java.util.List;

public class AtividadeDAO {

    private SQLiteDatabase db;
    private DatabaseConection con;

    public AtividadeDAO(Context context) {
        con = new DatabaseConection(context);
    }

    public boolean inserir(Atividade atividade) {
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", atividade.getTitulo());
        values.put("enunciado", atividade.getEnunciado());
        values.put("dificuldade", atividade.getDificuldade());
        values.put("exemploentrada", atividade.getExemploentrada());
        values.put("exemplosaida", atividade.getExemplosaida());
        values.put("dataEntrega", atividade.getDataEntrega());

        long res = db.insert(DatabaseConection.TABELA_ATIVIDADE, null, values);
        db.close();
        return res != -1;
    }

    public boolean atualizar(Atividade atividade) {
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", atividade.getTitulo());
        values.put("enunciado", atividade.getEnunciado());
        values.put("dificuldade", atividade.getDificuldade());
        values.put("exemploentrada", atividade.getExemploentrada());
        values.put("exemplosaida", atividade.getExemplosaida());
        values.put("dataEntrega", atividade.getDataEntrega());

        int res = db.update(DatabaseConection.TABELA_ATIVIDADE, values, "id = ?", new String[]{String.valueOf(atividade.getId())});
        db.close();
        return res > 0;
    }

    public boolean deletar(int id) {
        db = con.getWritableDatabase();
        int res = db.delete(DatabaseConection.TABELA_ATIVIDADE, "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return res > 0;
    }

    public List<Atividade> listar() {
        List<Atividade> lista = new ArrayList<>();
        db = con.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseConection.TABELA_ATIVIDADE, null);

        if (cursor.moveToFirst()) {
            do {
                Atividade at = new Atividade(
                        cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                        cursor.getString(cursor.getColumnIndexOrThrow("enunciado")),
                        cursor.getString(cursor.getColumnIndexOrThrow("dificuldade")),
                        cursor.getString(cursor.getColumnIndexOrThrow("exemploentrada")),
                        cursor.getString(cursor.getColumnIndexOrThrow("exemplosaida")),
                        cursor.getString(cursor.getColumnIndexOrThrow("dataEntrega"))
                );
                at.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                lista.add(at);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
}
