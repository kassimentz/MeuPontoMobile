package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface CalendarioJustificativasDAO {

    boolean salvar(CalendarioJustificativas calendarioJustificativas);
    boolean deleter(CalendarioJustificativas calendarioJustificativas);
    boolean atualizar(CalendarioJustificativas calendarioJustificativas);
    List<CalendarioJustificativas> listar();
    CalendarioJustificativas procurarPorId(Integer id);
    List<Calendar> getCalendarioJustificaticasByDate(Calendar inicio, Calendar fim);
    DatabaseHandler conectar(Context context);

}
