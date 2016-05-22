package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;
import com.edm.kassimentz.meupontomobile.model.Justificativa;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class CalendarioJustificativasDAOImpl implements CalendarioJustificativasDAO {

    private Context context;
    private static final String table = "calendario_justificativas";

    public CalendarioJustificativasDAOImpl(Context ctx){
        this.context = ctx;
    }

    //TODO
    @Override
    public List<CalendarioJustificativas> getCalendarioJustificaticasByDate(Calendar inicio, Calendar fim) {
        return null;
    }

    @Override
    public void salvar(CalendarioJustificativas calendarioJustificativas) {

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (data_hora, observacao, justificativa) VALUES (?, ?, ?)",
                new String[]{
                        String.valueOf(calendarioJustificativas.getData_hora().getTime()),
                        calendarioJustificativas.getObservacao(),
                        String.valueOf(calendarioJustificativas.getJustificativa().ordinal())
                });
    }

    @Override
    public void excluir(CalendarioJustificativas calendarioJustificativas) {

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id = ?",
                new String[]{
                        String.valueOf(calendarioJustificativas.getId())
                });

    }

    @Override
    public void atualizar(CalendarioJustificativas calendarioJustificativas) {

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET data_hora = ?, observacao = ?, justificativa = ? WHERE id = ?",
                new String[]{
                        String.valueOf(calendarioJustificativas.getData_hora().getTime()),
                        calendarioJustificativas.getObservacao(),
                        String.valueOf(calendarioJustificativas.getJustificativa().ordinal()),
                        String.valueOf(calendarioJustificativas.getId())
                });
    }

    @Override
    public List<CalendarioJustificativas> listar() {

        List<CalendarioJustificativas> calendariosJustificativas = new ArrayList<CalendarioJustificativas>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {
            CalendarioJustificativas calendarioJustificativa = new CalendarioJustificativas();

            calendarioJustificativa.setId(cv.getAsInteger("id"));
            calendarioJustificativa.setData_hora(new Date(cv.getAsLong("data_hora")));
            calendarioJustificativa.setObservacao(cv.getAsString("observacao"));

            Justificativa j = Justificativa.valueOf(cv.getAsString("justificativa"));
            calendarioJustificativa.setJustificativa(j);

            calendariosJustificativas.add(calendarioJustificativa);
        }

        return calendariosJustificativas;
    }

    @Override
    public CalendarioJustificativas procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table, id);

        CalendarioJustificativas calendarioJustificativa = new CalendarioJustificativas();

        calendarioJustificativa.setId(cv.getAsInteger("id"));
        calendarioJustificativa.setData_hora(new Date(cv.getAsLong("data_hora")));
        calendarioJustificativa.setObservacao(cv.getAsString("observacao"));

//        Justificativa j = Justificativa.valueOf(cv.getAsString("justificativa"));
//        calendarioJustificativa.setJustificativa(j);

        return calendarioJustificativa;
    }
}
