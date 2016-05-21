package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public abstract class DaoDB<T> implements DAO<T> {

    protected DatabaseHandler databaseHandler;
    protected SQLiteDatabase banco;

    public DatabaseHandler conectar(Context context){
        return databaseHandler = new DatabaseHandler(context);
    }
}
