package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class PeriodosTrabalhadosDAOImpl implements PeriodosTrabalhadosDAO {

    private Context context;
    private static final String table = "periodos_trabalhados";

    public PeriodosTrabalhadosDAOImpl(Context ctx){
        this.context = ctx;
    }

    /*TODO*/
    @Override
    public List<PeriodosTrabalhados> getPeriodosTrabalhadosPorData(Calendar inicio, Calendar fim) {
        return null;
    }

    @Override
    public void salvar(PeriodosTrabalhados periodosTrabalhados) {

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (data_hora_inicio, data_hora_fim) VALUES (?, ?)",
                new String[]{
                        String.valueOf(periodosTrabalhados.getData_hora_inicio().getTimeInMillis()),
                        String.valueOf(periodosTrabalhados.getData_hora_fim().getTimeInMillis())
                });
    }

    @Override
    public void excluir(PeriodosTrabalhados periodosTrabalhados) {

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id_periodos_trabalhados = ?",
                new String[]{
                        String.valueOf(periodosTrabalhados.getId())
                });
    }

    @Override
    public void atualizar(PeriodosTrabalhados periodosTrabalhados) {

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET data_hora_inicio = ?, data_hora_fim = ? WHERE id_periodos_trabalhados = ?",
                new String[]{
                        String.valueOf(periodosTrabalhados.getData_hora_inicio().getTimeInMillis()),
                        String.valueOf(periodosTrabalhados.getData_hora_fim().getTimeInMillis()),
                        String.valueOf(periodosTrabalhados.getId())
                });
    }

    @Override
    public List<PeriodosTrabalhados> listar() {

        List<PeriodosTrabalhados> periodosTrabalhadosList = new ArrayList<PeriodosTrabalhados>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {
            PeriodosTrabalhados periodosTrabalhados = new PeriodosTrabalhados();

            periodosTrabalhados.setId(cv.getAsInteger("id_periodos_trabalhados"));

            Calendar dataInicio = Calendar.getInstance();
            dataInicio.setTimeInMillis(cv.getAsLong("data_hora_inicio"));
            periodosTrabalhados.setData_hora_inicio(dataInicio);

            Calendar dataFim = Calendar.getInstance();
            dataFim.setTimeInMillis(cv.getAsLong("data_hora_fim"));
            periodosTrabalhados.setData_hora_fim(dataFim);

            periodosTrabalhadosList.add(periodosTrabalhados);
        }

        return periodosTrabalhadosList;
    }

    @Override
    public PeriodosTrabalhados procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table,
                new String[]{"id_calendario_justificativas, data_hora, observacao, justificativa"},"id_calendario_justificativas",id);

        PeriodosTrabalhados periodosTrabalhados = new PeriodosTrabalhados();

        periodosTrabalhados.setId(cv.getAsInteger("id_periodos_trabalhados"));

        Calendar dataInicio = Calendar.getInstance();
        dataInicio.setTimeInMillis(cv.getAsLong("data_hora_inicio"));
        periodosTrabalhados.setData_hora_inicio(dataInicio);

        Calendar dataFim = Calendar.getInstance();
        dataFim.setTimeInMillis(cv.getAsLong("data_hora_fim"));
        periodosTrabalhados.setData_hora_fim(dataFim);

        return periodosTrabalhados;
    }
}
