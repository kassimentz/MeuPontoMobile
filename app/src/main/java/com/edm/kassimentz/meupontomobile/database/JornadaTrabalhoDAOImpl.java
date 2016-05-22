package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.JornadaTrabalho;
import com.edm.kassimentz.meupontomobile.model.Periodo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class JornadaTrabalhoDAOImpl implements JornadaTrabalhoDAO {

    private Context context;
    private static final String table = "jornada_trabalho";

    public JornadaTrabalhoDAOImpl(Context ctx){
        this.context = ctx;
    }

    @Override
    public void salvar(JornadaTrabalho jornadaTrabalho) {

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" " +
                        "(duracao_intervalo, tempo_alerta_intervalo, hora_inicio_jornada, hora_saida_intervalo, hora_termino_jornada, horas_trabalho_dia, dias_trabalho_semana, trabalho_domingo, periodo_trabalho) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new String[]{
                        String.valueOf(jornadaTrabalho.getDuracao_intervalo()),
                        String.valueOf(jornadaTrabalho.getTempo_alerta_intervalo()),
                        String.valueOf(jornadaTrabalho.getHora_inicio_jornada().getTimeInMillis()),
                        String.valueOf(jornadaTrabalho.getHora_saida_intervalo().getTimeInMillis()),
                        String.valueOf(jornadaTrabalho.getHoras_trabalho_dia()),
                        String.valueOf(jornadaTrabalho.getDias_trabalho_semana()),
                        String.valueOf(jornadaTrabalho.isTrabalho_domingo()),
                        String.valueOf(jornadaTrabalho.getPeriodo().ordinal())
                });
    }

    @Override
    public void excluir(JornadaTrabalho jornadaTrabalho) {

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id_jornada_trabalho = ?",
                new String[]{
                        String.valueOf(jornadaTrabalho.getId())
                });
    }

    @Override
    public void atualizar(JornadaTrabalho jornadaTrabalho) {

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET duracao_intervalo = ?, tempo_alerta_intervalo = ?, hora_inicio_jornada = ? , hora_saida_intervalo = ? " +
                        " hora_termino_jornada = ?, horas_trabalho_dia = ?, dias_trabalho_semana = ?, trabalho_domingo = ?, periodo_trabalho = ? WHERE id_jornada_trabalho = ?",
                new String[]{
                        String.valueOf(jornadaTrabalho.getDuracao_intervalo()),
                        String.valueOf(jornadaTrabalho.getTempo_alerta_intervalo()),
                        String.valueOf(jornadaTrabalho.getHora_inicio_jornada().getTimeInMillis()),
                        String.valueOf(jornadaTrabalho.getHora_saida_intervalo().getTimeInMillis()),
                        String.valueOf(jornadaTrabalho.getHora_termino_jornada().getTimeInMillis()),
                        String.valueOf(jornadaTrabalho.getHoras_trabalho_dia()),
                        String.valueOf(jornadaTrabalho.getDias_trabalho_semana()),
                        String.valueOf(jornadaTrabalho.isTrabalho_domingo()),
                        String.valueOf(jornadaTrabalho.getPeriodo().ordinal()),
                        String.valueOf(jornadaTrabalho.getId())

                });
    }

    @Override
    public List<JornadaTrabalho> listar() {

        List<JornadaTrabalho> jornadaTrabalhoList = new ArrayList<JornadaTrabalho>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {
            JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();

            jornadaTrabalho.setId(cv.getAsInteger("id_jornada_trabalho"));
            jornadaTrabalho.setDuracao_intervalo(cv.getAsInteger("duracao_intervalo"));
            jornadaTrabalho.setTempo_alerta_intervalo(cv.getAsInteger("tempo_alerta_intervalo"));

            Calendar horaInicioJornada = Calendar.getInstance();
            horaInicioJornada.setTimeInMillis(cv.getAsLong("hora_inicio_jornada"));
            jornadaTrabalho.setHora_inicio_jornada(horaInicioJornada);

            Calendar horaSaidaIntervalo = Calendar.getInstance();
            horaSaidaIntervalo.setTimeInMillis(cv.getAsLong("hora_saida_intervalo"));
            jornadaTrabalho.setHora_saida_intervalo(horaSaidaIntervalo);

            Calendar horaTerminoJornada = Calendar.getInstance();
            horaTerminoJornada.setTimeInMillis(cv.getAsLong("hora_termino_jornada"));
            jornadaTrabalho.setHora_termino_jornada(horaTerminoJornada);

            jornadaTrabalho.setHoras_trabalho_dia(cv.getAsDouble("horas_trabalho_dia"));
            jornadaTrabalho.setDias_trabalho_semana(cv.getAsInteger("dias_trabalho_semana"));
            jornadaTrabalho.setTrabalho_domingo(cv.getAsBoolean("trabalho_domingo"));

            Periodo p = Periodo.valueOf(cv.getAsString("periodo_trabalho"));
            jornadaTrabalho.setPeriodo(p);

            jornadaTrabalhoList.add(jornadaTrabalho);
        }

        return jornadaTrabalhoList;
    }

    @Override
    public JornadaTrabalho procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table,
                new String[]{"id_jornada_trabalho, duracao_intervalo, tempo_alerta_intervalo, hora_inicio_jornada, hora_saida_intervalo, " +
                        "hora_termino_jornada, horas_trabalho_dia, dias_trabalho_semana, trabalho_domingo, periodo_trabalho"},
                        "id_jornada_trabalho",id);

        JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();

        jornadaTrabalho.setId(cv.getAsInteger("id_jornada_trabalho"));
        jornadaTrabalho.setDuracao_intervalo(cv.getAsInteger("duracao_intervalo"));
        jornadaTrabalho.setTempo_alerta_intervalo(cv.getAsInteger("tempo_alerta_intervalo"));

        Calendar horaInicioJornada = Calendar.getInstance();
        horaInicioJornada.setTimeInMillis(cv.getAsLong("hora_inicio_jornada"));
        jornadaTrabalho.setHora_inicio_jornada(horaInicioJornada);

        Calendar horaSaidaIntervalo = Calendar.getInstance();
        horaSaidaIntervalo.setTimeInMillis(cv.getAsLong("hora_saida_intervalo"));
        jornadaTrabalho.setHora_saida_intervalo(horaSaidaIntervalo);

        Calendar horaTerminoJornada = Calendar.getInstance();
        horaTerminoJornada.setTimeInMillis(cv.getAsLong("hora_termino_jornada"));
        jornadaTrabalho.setHora_termino_jornada(horaTerminoJornada);

        jornadaTrabalho.setHoras_trabalho_dia(cv.getAsDouble("horas_trabalho_dia"));
        jornadaTrabalho.setDias_trabalho_semana(cv.getAsInteger("dias_trabalho_semana"));
        jornadaTrabalho.setTrabalho_domingo(cv.getAsBoolean("trabalho_domingo"));

        Periodo p = Periodo.valueOf(cv.getAsString("periodo_trabalho"));
        jornadaTrabalho.setPeriodo(p);

        return jornadaTrabalho;
    }
}
