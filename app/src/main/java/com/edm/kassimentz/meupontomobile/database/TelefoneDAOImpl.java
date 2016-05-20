package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class TelefoneDAOImpl extends TelefoneDAOBase {

    DatabaseHandler databaseHandler;
    String tabela = "telefone";

    public TelefoneDAOImpl(Context context){
        databaseHandler = conectar(context);
    }

    @Override
    public boolean salvar(Telefone telefone) {
        return false;
    }

    @Override
    public boolean deletar(Telefone telefone) {
        return false;
    }

    @Override
    public boolean atualizar(Telefone telefone) {
        return false;
    }

    @Override
    public List<Telefone> listar() {
        return null;
    }

    @Override
    public Telefone procurarPorId(Integer id) {
        return null;
    }
}
