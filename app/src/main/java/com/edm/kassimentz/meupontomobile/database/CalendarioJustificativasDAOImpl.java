package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class CalendarioJustificativasDAOImpl extends DaoDB<CalendarioJustificativas> implements CalendarioJustificativasDAO {

    @Override
    public List<Calendar> getCalendarioJustificaticasByDate(Calendar inicio, Calendar fim) {
        return null;
    }

    @Override
    public boolean salvar(CalendarioJustificativas calendarioJustificativas) {
        return false;
    }

    @Override
    public boolean deleter(CalendarioJustificativas calendarioJustificativas) {
        return false;
    }

    @Override
    public boolean atualizar(CalendarioJustificativas calendarioJustificativas) {
        return false;
    }

    @Override
    public List<CalendarioJustificativas> listar() {
        return null;
    }

    @Override
    public CalendarioJustificativas procurarPorId(Integer id) {
        return null;
    }
}
