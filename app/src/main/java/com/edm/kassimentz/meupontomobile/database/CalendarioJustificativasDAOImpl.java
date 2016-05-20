package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;
import com.edm.kassimentz.meupontomobile.model.Justificativa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class CalendarioJustificativasDAOImpl extends CalendarioJustificativaDAOBase {


    DatabaseHandler databaseHandler;
    String tabela = "calendario_justificativas";

    public CalendarioJustificativasDAOImpl(Context context){
        databaseHandler = conectar(context);
    }


    @Override
    public boolean salvar(CalendarioJustificativas calendarioJustificativas) {

        SQLiteDatabase banco = databaseHandler.getWritableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("data_hora", calendarioJustificativas.getData_hora().getTimeInMillis());
        dados.put("observacao", calendarioJustificativas.getObservacao());
        dados.put("justificativa", calendarioJustificativas.getJustificativa().ordinal());

        /*
        * Color color = Color.GREEN;
        int ordinal = color.ordinal(); // ordinal == 1
        // save ordinal

        // retrieve from saved value
        Color savedColor = Color.values()[savedOrdinal];

        * */

        boolean result = banco.insert(tabela, null, dados) > 0;
        banco.close();
        Log.d(tabela,"inserido com sucesso");
        return(result);
    }

    @Override
    public boolean deletar(CalendarioJustificativas calendarioJustificativas) {

        SQLiteDatabase banco = databaseHandler.getWritableDatabase();
        boolean result = banco.delete(tabela, "id_funcionario=?",
                new String[]{calendarioJustificativas.getId().toString()}) > 0;
        banco.close();
        return(result);
    }

    @Override
    public boolean atualizar(CalendarioJustificativas calendarioJustificativas) {

        SQLiteDatabase banco = databaseHandler.getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("data_hora", calendarioJustificativas.getData_hora().toString());
        dados.put("observacao", calendarioJustificativas.getObservacao());
        dados.put("justificativa", calendarioJustificativas.getJustificativa().ordinal());
        boolean result = banco.update(tabela, dados, "id_calendario_justificativas=?",
                new String[]{calendarioJustificativas.getId().toString()}) > 0;
        banco.close();
        return (result);
    }

    @Override
    public List<CalendarioJustificativas> listar() {



        List<CalendarioJustificativas> calendarioJustificativas = new ArrayList<CalendarioJustificativas>();
        SQLiteDatabase banco = databaseHandler.getReadableDatabase();
        String[] colunas = {"data_hora","observacao","justificativa"};
        Cursor c = banco.query(tabela,colunas,
                null, null, null, null, null);

        while(c.moveToNext()) {
            Calendar calendar = Calendar.getInstance();
            CalendarioJustificativas cj = new CalendarioJustificativas();
            calendar.setTimeInMillis(c.getLong(c.getColumnIndex("data_hora")));
            cj.setData_hora(calendar);
            cj.setObservacao(c.getString(c.getColumnIndex("observacao")));
            Justificativa j = Justificativa.valueOf(c.getString(c.getColumnIndex("justificativa")));
            cj.setJustificativa(j);

            calendarioJustificativas.add(cj);
        }
        return calendarioJustificativas;
    }

    @Override
    public CalendarioJustificativas procurarPorId(Integer id) {

        SQLiteDatabase banco = databaseHandler.getReadableDatabase();

        String[] colunas = {"data_hora","observacao","justificativa"};
        Cursor c = banco.query(tabela,colunas,
                "id_calendario_justificativas=?", new String[]{id.toString()},
                null, null, null);

        while(c.moveToNext()) {
            Calendar calendar = Calendar.getInstance();
            CalendarioJustificativas cj = new CalendarioJustificativas();
            calendar.setTimeInMillis(c.getLong(c.getColumnIndex("data_hora")));
            cj.setData_hora(calendar);
            cj.setObservacao(c.getString(c.getColumnIndex("observacao")));
            Justificativa j = Justificativa.valueOf(c.getString(c.getColumnIndex("justificativa")));
            cj.setJustificativa(j);

            return cj;
        }
        return(null);
    }

    @Override
    public List<Calendar> getCalendarioJustificaticasByDate(Calendar inicio, Calendar fim) {
        return null;
    }
}
