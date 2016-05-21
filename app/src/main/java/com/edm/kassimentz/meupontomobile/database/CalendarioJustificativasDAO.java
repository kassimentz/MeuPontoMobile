package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface CalendarioJustificativasDAO extends GenericDAO<CalendarioJustificativas>{

    List<CalendarioJustificativas> getCalendarioJustificaticasByDate(Calendar inicio, Calendar fim);

}
