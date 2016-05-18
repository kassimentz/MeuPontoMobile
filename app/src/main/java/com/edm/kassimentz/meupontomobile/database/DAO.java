package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface DAO<T> {

    public boolean salvar(T t);
    public boolean deleter(T t);
    public boolean atualizar(T t);
    public List<T> listar();
    public T procurarPorId(Integer id);
}
