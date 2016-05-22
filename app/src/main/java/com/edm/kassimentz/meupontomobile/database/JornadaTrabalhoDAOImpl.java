package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.JornadaTrabalho;
import com.edm.kassimentz.meupontomobile.model.Periodo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, Object> map = verificaCamposNulos(jornadaTrabalho);

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" " +
                        "(duracao_intervalo, tempo_alerta_intervalo, hora_inicio_jornada, hora_saida_intervalo, hora_termino_jornada, horas_trabalho_dia, dias_trabalho_semana, trabalho_domingo, periodo_trabalho) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new String[]{
                        String.valueOf((Integer)map.get("duracao_intervalo")),
                        String.valueOf((Integer)map.get("tempo_alerta_intervalo")),
                        String.valueOf((Long)map.get("hora_inicio_jornada")),
                        String.valueOf((Long)map.get("hora_saida_intervalo")),
                        String.valueOf((Double)map.get("horas_trabalho_dia")),
                        String.valueOf((Integer)map.get("dias_trabalho_semana")),
                        String.valueOf((Boolean)map.get("trabalho_domingo")),
                        String.valueOf((Integer)map.get("periodo"))
                });
    }


    @Override
    public void excluir(JornadaTrabalho jornadaTrabalho) {

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id = ?",
                new String[]{
                        String.valueOf(jornadaTrabalho.getId())
                });
    }

    @Override
    public void atualizar(JornadaTrabalho jornadaTrabalho) {

        Map<String, Object> map = verificaCamposNulos(jornadaTrabalho);

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET duracao_intervalo = ?, tempo_alerta_intervalo = ?, hora_inicio_jornada = ? , hora_saida_intervalo = ? " +
                        " hora_termino_jornada = ?, horas_trabalho_dia = ?, dias_trabalho_semana = ?, trabalho_domingo = ?, periodo_trabalho = ? WHERE id = ?",
                new String[]{
                        String.valueOf((Integer)map.get("duracao_intervalo")),
                        String.valueOf((Integer)map.get("tempo_alerta_intervalo")),
                        String.valueOf((Long)map.get("hora_inicio_jornada")),
                        String.valueOf((Long)map.get("hora_saida_intervalo")),
                        String.valueOf((Double)map.get("horas_trabalho_dia")),
                        String.valueOf((Integer)map.get("dias_trabalho_semana")),
                        String.valueOf((Boolean)map.get("trabalho_domingo")),
                        String.valueOf((Integer)map.get("periodo")),
                        String.valueOf(jornadaTrabalho.getId())

                });
    }

    @Override
    public List<JornadaTrabalho> listar() {

        List<JornadaTrabalho> jornadaTrabalhoList = new ArrayList<JornadaTrabalho>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {
            JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();

            jornadaTrabalho.setId(cv.getAsInteger("id"));
            jornadaTrabalho.setDuracao_intervalo(cv.getAsInteger("duracao_intervalo"));
            jornadaTrabalho.setTempo_alerta_intervalo(cv.getAsInteger("tempo_alerta_intervalo"));
            jornadaTrabalho.setHora_inicio_jornada(new Date(cv.getAsLong("hora_inicio_jornada")));
            jornadaTrabalho.setHora_saida_intervalo(new Date(cv.getAsLong("hora_saida_intervalo")));
            jornadaTrabalho.setHora_termino_jornada(new Date(cv.getAsLong("hora_termino_jornada")));

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

        ContentValues cv = DB.byId(this.context, table, id);

        JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();

        jornadaTrabalho.setId(cv.getAsInteger("id"));
        jornadaTrabalho.setDuracao_intervalo(cv.getAsInteger("duracao_intervalo"));
        jornadaTrabalho.setTempo_alerta_intervalo(cv.getAsInteger("tempo_alerta_intervalo"));
//        jornadaTrabalho.setHora_inicio_jornada(new Date(cv.getAsLong("hora_inicio_jornada")));
  //      jornadaTrabalho.setHora_saida_intervalo(new Date(cv.getAsLong("hora_saida_intervalo")));
    //    jornadaTrabalho.setHora_termino_jornada(new Date(cv.getAsLong("hora_termino_jornada")));
        jornadaTrabalho.setHoras_trabalho_dia(cv.getAsDouble("horas_trabalho_dia"));
        jornadaTrabalho.setDias_trabalho_semana(cv.getAsInteger("dias_trabalho_semana"));
        jornadaTrabalho.setTrabalho_domingo(cv.getAsBoolean("trabalho_domingo"));

       // Periodo p = Periodo.valueOf(cv.getAsString("periodo_trabalho"));
        //jornadaTrabalho.setPeriodo(p);

        return jornadaTrabalho;
    }

    private Map<String, Object> verificaCamposNulos(JornadaTrabalho jornadaTrabalho) {

        Map<String, Object> map = new HashMap<String, Object>();

        Integer duracao_intervalo = null, tempo_alerta_intervalo = null, dias_trabalho_semana = null, periodo = null;
        Long hora_inicio_jornada = null, hora_saida_intervalo = null, hora_termino_jornada = null;
        Double horas_trabalho_dia = null;
        Boolean trabalho_domingo = null;

        if(jornadaTrabalho.getDuracao_intervalo() != null){
            duracao_intervalo = jornadaTrabalho.getDuracao_intervalo();
        }
        if(jornadaTrabalho.getTempo_alerta_intervalo() != null){
            tempo_alerta_intervalo = jornadaTrabalho.getTempo_alerta_intervalo();
        }
        if(jornadaTrabalho.getHora_inicio_jornada() != null){
            hora_inicio_jornada = jornadaTrabalho.getHora_inicio_jornada().getTime();
        }
        if(jornadaTrabalho.getHora_saida_intervalo() != null){
            hora_saida_intervalo = jornadaTrabalho.getHora_saida_intervalo().getTime();
        }
        if(jornadaTrabalho.getHoras_trabalho_dia() != null){
            horas_trabalho_dia = jornadaTrabalho.getHoras_trabalho_dia();
        }
        if(jornadaTrabalho.getDias_trabalho_semana() != null){
            dias_trabalho_semana = jornadaTrabalho.getDias_trabalho_semana();
        }
        if(jornadaTrabalho.isTrabalho_domingo() != null){
            trabalho_domingo = jornadaTrabalho.isTrabalho_domingo();
        }
        if(jornadaTrabalho.getPeriodo() != null){
            periodo = jornadaTrabalho.getPeriodo().ordinal();
        }

        map.put("duracao_intervalo", duracao_intervalo);
        map.put("tempo_alerta_intervalo", tempo_alerta_intervalo);
        map.put("hora_inicio_jornada", hora_inicio_jornada);
        map.put("hora_saida_intervalo", hora_saida_intervalo);
        map.put("horas_trabalho_dia", horas_trabalho_dia);
        map.put("dias_trabalho_semana", dias_trabalho_semana);
        map.put("trabalho_domingo", trabalho_domingo);
        map.put("periodo", periodo);

        return map;

    }
}
