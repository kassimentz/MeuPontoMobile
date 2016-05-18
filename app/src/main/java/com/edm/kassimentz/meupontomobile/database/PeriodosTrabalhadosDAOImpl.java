package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class PeriodosTrabalhadosDAOImpl extends DaoDB<PeriodosTrabalhados> implements PeriodosTrabalhadosDAO {
    @Override
    public List<PeriodosTrabalhados> getPeriodosTrabalhadosPorData(Calendar inicio, Calendar fim) {
        return null;
    }

    @Override
    public boolean salvar(PeriodosTrabalhados periodosTrabalhados) {
        return false;
    }

    @Override
    public boolean deleter(PeriodosTrabalhados periodosTrabalhados) {
        return false;
    }

    @Override
    public boolean atualizar(PeriodosTrabalhados periodosTrabalhados) {
        return false;
    }

    @Override
    public List<PeriodosTrabalhados> listar() {
        return null;
    }

    @Override
    public PeriodosTrabalhados procurarPorId(Integer id) {
        return null;
    }
}
