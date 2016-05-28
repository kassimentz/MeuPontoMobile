package com.edm.kassimentz.meupontomobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.edm.kassimentz.meupontomobile.Adapters.FuncionarioAdapter;
import com.edm.kassimentz.meupontomobile.database.FuncionarioDAOImpl;

public class MainActivity extends AppCompatActivity {

    FuncionarioDAOImpl dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new FuncionarioDAOImpl(this.getBaseContext());
        getLista();
    }

    private void getLista() {


        ListView lista = (ListView) findViewById(R.id.listFuncionarios);
        FuncionarioAdapter adapter = new FuncionarioAdapter(MainActivity.this, dao.listar());
        lista.setAdapter(adapter);

    }
}
