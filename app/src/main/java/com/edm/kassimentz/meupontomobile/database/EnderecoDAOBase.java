package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Cassio on 19/05/16.
 */
public abstract class EnderecoDAOBase implements EnderecoDAO{

    protected DatabaseHandler databaseHandler;
    protected SQLiteDatabase banco;

    @Override
    public DatabaseHandler conectar(Context context) {
        return databaseHandler = new DatabaseHandler(context);
    }
}
