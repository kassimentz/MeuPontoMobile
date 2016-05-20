package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kassi on 19/05/16.
 */
public abstract class CalendarioJustificativaDAOBase implements CalendarioJustificativasDAO{

    protected DatabaseHandler databaseHandler;
    protected SQLiteDatabase banco;

    @Override
    public DatabaseHandler conectar(Context context) {
        return databaseHandler = new DatabaseHandler(context);
    }
}
