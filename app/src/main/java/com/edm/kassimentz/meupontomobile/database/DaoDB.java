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


    @Override
    public Cursor countAll(Context context, String table) {
        SQLiteDatabase bd = new DatabaseHandler(context).getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM " + table;
        Cursor cursor = bd.rawQuery(sql, null);
        bd.close();
        return cursor;
    }

    @Override
    public Cursor getAll(Context context, String table, String[] colums) {
        SQLiteDatabase bd = new DatabaseHandler(context).getReadableDatabase();
        Cursor cursor = bd.query(table, colums, null, null, null, null, "area ASC");
        bd.close();
        return cursor;
    }
}
