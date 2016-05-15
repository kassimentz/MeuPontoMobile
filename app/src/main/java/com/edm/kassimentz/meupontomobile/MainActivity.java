package com.edm.kassimentz.meupontomobile;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edm.kassimentz.meupontomobile.database.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler bd = new DatabaseHandler(this.getBaseContext());
        SQLiteDatabase database =  bd.getWritableDatabase();

    }
}
