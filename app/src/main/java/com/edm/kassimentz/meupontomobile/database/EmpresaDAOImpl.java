package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edm.kassimentz.meupontomobile.model.Empresa;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EmpresaDAOImpl extends EmpresaDAOBase {

    DatabaseHandler databaseHandler;
    String tabela = "empresa";

    public EmpresaDAOImpl(Context context){
        databaseHandler = conectar(context);
    }

    @Override
    public boolean salvar(Empresa empresa) {

        SQLiteDatabase banco = databaseHandler.getWritableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("nome", empresa.getNome());
        dados.put("id_endereco", empresa.getEndereco().getId());
        //salvar os telefones na outra tabela
        boolean result = banco.insert(tabela, null, dados) > 0;
        banco.close();
        Log.d(tabela,"inserido com sucesso");
        return(result);
    }

    @Override
    public boolean deletar(Empresa calendarioJustificativas) {
        return false;
    }

    @Override
    public boolean atualizar(Empresa calendarioJustificativas) {
        return false;
    }

    @Override
    public List<Empresa> listar() {
        return null;
    }

    @Override
    public Empresa procurarPorId(Integer id) {
        return null;
    }
}
