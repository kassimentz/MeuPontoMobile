package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.Periodo;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class PeriodoDAOImpl extends DaoDB<Periodo> implements PeriodoDAO {
    @Override
    public Periodo procurarPorId(Integer id) {
        return null;
    }

    @Override
    public boolean salvar(Periodo periodo) {
        return false;
    }

    @Override
    public boolean deleter(Periodo periodo) {
        return false;
    }

    @Override
    public boolean atualizar(Periodo periodo) {
        return false;
    }

    @Override
    public List<Periodo> listar() {
        return null;
    }
}
