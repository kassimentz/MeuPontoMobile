package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edm.kassimentz.meupontomobile.model.Funcionario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class FuncionarioDAOImpl extends FuncionarioDAOBase{

    DatabaseHandler databaseHandler;
    String tabela = "funcionario";

    public FuncionarioDAOImpl(Context context){
        databaseHandler = conectar(context);
    }


    @Override
    public boolean salvar(Funcionario funcionario) {
        SQLiteDatabase banco = databaseHandler.getWritableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("nome", funcionario.getNome());
        dados.put("cpf", funcionario.getCpf());
        dados.put("cargo", funcionario.getCargo());

        boolean result = banco.insert(tabela, null, dados) > 0;
        banco.close();
        Log.d(tabela,"inserido com sucesso");
        return(result);

    }

    @Override
    public boolean deletar(Funcionario funcionario) {
        SQLiteDatabase banco = databaseHandler.getWritableDatabase();
        boolean result = banco.delete(tabela, "id_funcionario=?",
                new String[]{funcionario.getId().toString()}) > 0;
        banco.close();
        return(result);

    }

    @Override
    public boolean atualizar(Funcionario funcionario) {
        SQLiteDatabase banco = databaseHandler.getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", funcionario.getNome());
        dados.put("cpf", funcionario.getCpf());
        dados.put("cargo", funcionario.getCargo());
        dados.put("id_empresa", funcionario.getEmpresa().getId());
        dados.put("id_jornada_trabalho", funcionario.getJornadaTrabalho().getId());
        boolean result = banco.update(tabela, dados, "id_funcionario=?",
                new String[]{funcionario.getId().toString()}) > 0;
        banco.close();
        return (result);

    }

    @Override
    public List<Funcionario> listar() {

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        SQLiteDatabase banco = databaseHandler.getReadableDatabase();
        String[] colunas = {"nome","cpf","cargo"};
        Cursor c = banco.query(tabela,colunas,
                null, null, null, null, null);

        while(c.moveToNext()) {
            Funcionario f= new Funcionario();
            f.setNome(c.getString(c.getColumnIndex("nome")));
            f.setCpf(c.getString(c.getColumnIndex("cpf")));
            f.setCargo(c.getString(c.getColumnIndex("cargo")));

            funcionarios.add(f);
        }
        return funcionarios;
    }

    @Override
    public Funcionario procurarPorId(Integer id) {
        SQLiteDatabase banco = databaseHandler.getReadableDatabase();

        String[] colunas = {"nome","cpf","cargo"};
        Cursor c = banco.query(tabela,colunas,
                "id_funcionario=?", new String[]{id.toString()},
                null, null, null);

        while(c.moveToNext()) {
            Funcionario f= new Funcionario();
            f.setNome(c.getString(c.getColumnIndex("nome")));
            f.setCpf(c.getString(c.getColumnIndex("cpf")));
            f.setCargo(c.getString(c.getColumnIndex("cargo")));

            return f;
        }
        return(null);
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
