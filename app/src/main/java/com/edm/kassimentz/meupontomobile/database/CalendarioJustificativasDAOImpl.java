package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;
import com.edm.kassimentz.meupontomobile.model.Justificativa;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, Object> map = verificaCamposNulos(calendarioJustificativas);

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (data_hora, observacao, justificativa) VALUES (?, ?, ?)",
                new String[]{
                        String.valueOf(map.get("data_hora")),
                        String.valueOf(map.get("observacao")),
                        String.valueOf(map.get("justificativa"))
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

        Map<String, Object> map = verificaCamposNulos(calendarioJustificativas);

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET data_hora = ?, observacao = ?, justificativa = ? WHERE id = ?",
                new String[]{
                        String.valueOf(map.get("data_hora")),
                        String.valueOf(map.get("observacao")),
                        String.valueOf(map.get("justificativa")),
                        String.valueOf(calendarioJustificativas.getId())
                });
    }

    @Override
    public List<CalendarioJustificativas> listar() {

        List<CalendarioJustificativas> calendariosJustificativas = new ArrayList<CalendarioJustificativas>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {
            CalendarioJustificativas calendarioJustificativa = new CalendarioJustificativas();

            Map<String, Object> map = verificaListaCamposNulos(cv);

            calendarioJustificativa.setId(cv.getAsInteger("id"));
            calendarioJustificativa.setData_hora((Date)map.get("data_hora"));
            calendarioJustificativa.setObservacao((String)map.get("observacao"));
            calendarioJustificativa.setJustificativa((Justificativa)map.get("justificativa"));

            calendariosJustificativas.add(calendarioJustificativa);
        }

        return calendariosJustificativas;
    }

    @Override
    public CalendarioJustificativas procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table, id);

        CalendarioJustificativas calendarioJustificativa = new CalendarioJustificativas();

        Map<String, Object> map = verificaListaCamposNulos(cv);

        calendarioJustificativa.setId(cv.getAsInteger("id"));
        calendarioJustificativa.setData_hora((Date)map.get("data_hora"));
        calendarioJustificativa.setObservacao((String)map.get("observacao"));
        calendarioJustificativa.setJustificativa((Justificativa)map.get("justificativa"));

        return calendarioJustificativa;
    }

    private Map<String, Object> verificaListaCamposNulos(ContentValues cv) {
        Map<String, Object> map = new HashMap<String, Object>();
        Date data_hora = null;
        Justificativa justificativa = null;
        String observacao = null;

        if(cv.getAsLong("data_hora") != null){
            data_hora = new Date(cv.getAsLong("data_hora"));
        }
        if(cv.getAsString("observacao") != null){
            observacao = cv.getAsString("observacao");
        }
        if(Justificativa.values()[cv.getAsInteger("justificativa")] != null){
            justificativa = Justificativa.values()[cv.getAsInteger("justificativa")];
        }

        map.put("data_hora", data_hora);
        map.put("observacao", observacao);
        map.put("justificativa", justificativa);
        return map;
    }

    private Map<String, Object> verificaCamposNulos(CalendarioJustificativas calendarioJustificativas) {

        Map<String, Object> map = new HashMap<String, Object>();
        Long data_hora = null;
        Integer justificativa = null;
        String observacao = null;

        if(calendarioJustificativas.getData_hora() != null){
            data_hora = calendarioJustificativas.getData_hora().getTime();
        }
        if(calendarioJustificativas.getObservacao() != null){
            observacao = calendarioJustificativas.getObservacao();
        }
        if(calendarioJustificativas.getJustificativa() != null){
            justificativa = calendarioJustificativas.getJustificativa().ordinal();
        }

        map.put("data_hora", data_hora);
        map.put("observacao", observacao);
        map.put("justificativa", justificativa);

        return map;
    }
}
