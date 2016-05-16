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

    public SQLiteDatabase getDb();
    public boolean criarDb(Context context);
    public boolean conectarDb();
    public Cursor countAll(Context context, String table);
    public Cursor getAll(Context context, String table, String[] colums);
}
