package com.edm.kassimentz.meupontomobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.edm.kassimentz.meupontomobile.database.FuncionarioDAOImpl;
import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FuncionarioDAOImpl funcionarioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        funcionarioDAO = new FuncionarioDAOImpl(this.getBaseContext());


    }

    public void salvar (View v){

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Kassiane Mentz");
        funcionario.setCargo("Desenvolvedora");
        funcionario.setCpf("01119475023");

        funcionarioDAO.salvar(funcionario);

        List<Funcionario> listFuncionarios = funcionarioDAO.listar();

        for (Funcionario f: listFuncionarios) {
            Log.d("table", f.getNome());
            Log.d("table", f.getCargo());
        }
    }
}
