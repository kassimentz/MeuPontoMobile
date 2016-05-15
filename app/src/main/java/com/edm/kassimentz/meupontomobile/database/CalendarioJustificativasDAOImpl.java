package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Cassio on 14/05/16.
 */
public class CalendarioJustificativasDAOImpl extends DaoDB<CalendarioJustificativas> implements CalendarioJustificativasDAO {
    @Override
    public List<Calendar> getCalendarioJustificaticasByDate(Calendar inicio, Calendar fim) {
        return null;
    }
}
