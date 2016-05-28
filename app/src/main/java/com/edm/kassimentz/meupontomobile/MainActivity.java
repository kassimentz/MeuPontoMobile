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

import java.util.Map;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
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

        if (id == R.id.sair) {
            finish();
        }

        if(id == R.id.action_list_funcionarios){
            Intent main = new Intent(MainActivity.this, ListaFuncionariosActivity.class);
            startActivity(main);
        }

        if(id == R.id.action_list_enderecos){
            texto = "Ainda será implementado";
            toast= Toast.makeText(context, texto,duracao);
            toast.show();
        }

        if(id == R.id.action_list_telefones){
            texto = "Ainda será implementado";
            toast= Toast.makeText(context, texto,duracao);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
