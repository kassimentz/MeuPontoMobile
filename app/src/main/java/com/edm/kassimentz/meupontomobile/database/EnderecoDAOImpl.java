package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Endereco;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EnderecoDAOImpl extends EmpresaDAOBase {

    DatabaseHandler databaseHandler;
    String tabela = "endereco";

    public EnderecoDAOImpl(Context context){
        databaseHandler = conectar(context);
    }

    @Override
    public boolean salvar(Empresa empresa) {
        return false;
    }

    @Override
    public boolean deletar(Empresa empres) {
        return false;
    }

    @Override
    public boolean atualizar(Empresa empresa) {
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
