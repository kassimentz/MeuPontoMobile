package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import android.database.Cursor;

import com.edm.kassimentz.meupontomobile.model.Funcionario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class FuncionarioDAOImpl extends DaoDB<Funcionario> implements FuncionarioDAO{

    Context context;
     String tabela = "funcionario";

    public FuncionarioDAOImpl(Context ctx){
        this.context = ctx;

    }

    public List<Funcionario> buscarTodos(){

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        String[] colunas = {"nome","cpf","cargo"};
        Cursor c = super.getAll(this.context, tabela,colunas);

        while(c.moveToNext()) {
            Funcionario f= new Funcionario();
            f.setNome(c.getString(c.getColumnIndex("nome")));
            f.setCpf(c.getInt(c.getColumnIndex("cpf")));
            f.setCargo(c.getString(c.getColumnIndex("cargo")));

            funcionarios.add(f);
        }
        return funcionarios;
    }

    @Override
    public void baterPonto(Calendar hora) {

    }

    @Override
    public void emitirRelatorio() {

    }

    @Override
    public void fecharPeriodo(int periodo) {

    }
}
