package com.edm.kassimentz.meupontomobile.database;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class JustificativaDAOImpl extends DaoDB<JustificativaDAO> implements JustificativaDAO{
    @Override
    public boolean salvar(JustificativaDAO justificativaDAO) {
        return false;
    }

    @Override
    public boolean deleter(JustificativaDAO justificativaDAO) {
        return false;
    }

    @Override
    public boolean atualizar(JustificativaDAO justificativaDAO) {
        return false;
    }

    @Override
    public List<JustificativaDAO> listar() {
        return null;
    }

    @Override
    public JustificativaDAO procurarPorId(Integer id) {
        return null;
    }
}
