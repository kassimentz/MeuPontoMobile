package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface PeriodosTrabalhadosDAO extends GenericDAO<PeriodosTrabalhados>{

    public List<PeriodosTrabalhados> getPeriodosTrabalhadosPorData(Calendar inicio, Calendar fim);

}
