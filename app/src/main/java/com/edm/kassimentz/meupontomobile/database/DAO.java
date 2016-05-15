package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.Cursor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kassi on 14/05/16.
 */
public interface DAO<T> {

    public Cursor countAll(Context context, String table);
    public Cursor getAll(Context context, String table, String[] colums);
}
