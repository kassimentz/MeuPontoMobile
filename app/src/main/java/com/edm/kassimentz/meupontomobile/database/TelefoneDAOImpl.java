package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class TelefoneDAOImpl extends DaoDB<Telefone> implements TelefoneDAO {
    @Override
    public Telefone procurarPorId(Integer id) {
        return null;
    }

    @Override
    public boolean salvar(Telefone telefone) {
        return false;
    }

    @Override
    public boolean deleter(Telefone telefone) {
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
}
