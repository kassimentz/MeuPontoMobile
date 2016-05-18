package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.Empresa;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EmpresaDAOImpl extends DaoDB<Empresa> implements EmpresaDAO {
    @Override
    public boolean salvar(Empresa empresa) {
        return false;
    }

    @Override
    public boolean deleter(Empresa empresa) {
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
