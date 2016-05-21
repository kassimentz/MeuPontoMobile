package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface GenericDAO<T> {

    public void salvar(T entidade);
    public void excluir(T entidade);
    public void atualizar(T entidade);
    public List<T> listar();
    public T procurarPorId(Integer id);
}
