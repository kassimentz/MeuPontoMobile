package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface PeriodosTrabalhadosDAO {

    boolean salvar(PeriodosTrabalhados periodosTrabalhados);
    boolean deletar(PeriodosTrabalhados periodosTrabalhados);
    boolean atualizar(PeriodosTrabalhados periodosTrabalhados);
    List<PeriodosTrabalhados> listar();
    PeriodosTrabalhados procurarPorId(Integer id);
    DatabaseHandler conectar(Context context);

    public List<PeriodosTrabalhados> getPeriodosTrabalhadosPorData(Calendar inicio, Calendar fim);

}
