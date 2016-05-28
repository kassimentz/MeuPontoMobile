package com.edm.kassimentz.meupontomobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.edm.kassimentz.meupontomobile.adapters.FuncionarioAdapter;
import com.edm.kassimentz.meupontomobile.database.FuncionarioDAOImpl;

public class ListaFuncionariosActivity extends AppCompatActivity {

    FuncionarioDAOImpl dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionarios);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dao = new FuncionarioDAOImpl(this.getBaseContext());
        getLista();
    }

    private void getLista() {

        ListView lista = (ListView) findViewById(R.id.listViewFuncionarios);
        FuncionarioAdapter adapter = new FuncionarioAdapter(ListaFuncionariosActivity.this, dao.listar());
        lista.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_lista_funcionarios_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Toast toast;
        Context context = getApplicationContext();
        String texto = null;
        int duracao = Toast.LENGTH_SHORT;

        if (id == R.id.action_settings) {
            texto = "Ainda será implementado";
            toast= Toast.makeText(context, texto,duracao);
            toast.show();
        }

        if(id == R.id.action_back){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
