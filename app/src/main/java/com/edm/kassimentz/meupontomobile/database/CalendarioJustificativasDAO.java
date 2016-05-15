package com.edm.kassimentz.meupontomobile.database;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Cassio on 14/05/16.
 */
public interface CalendarioJustificativasDAO {

    public List<Calendar> getCalendarioJustificaticasByDate(Calendar inicio, Calendar fim);
}
