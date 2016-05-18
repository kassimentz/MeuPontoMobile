package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
