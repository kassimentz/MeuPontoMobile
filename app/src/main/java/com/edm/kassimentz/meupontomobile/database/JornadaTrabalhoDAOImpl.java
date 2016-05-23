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
                        "(duracao_intervalo, tempo_alerta_intervalo, hora_inicio_jornada, hora_saida_intervalo, " +
                        "hora_termino_jornada, horas_trabalho_dia, dias_trabalho_semana, trabalho_domingo, periodo_trabalho) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new String[]{
                        String.valueOf(map.get("duracao_intervalo")),
                        String.valueOf(map.get("tempo_alerta_intervalo")),
                        String.valueOf(map.get("hora_inicio_jornada")),
                        String.valueOf(map.get("hora_termino_jornada")),
                        String.valueOf(map.get("hora_saida_intervalo")),
                        String.valueOf(map.get("horas_trabalho_dia")),
                        String.valueOf(map.get("dias_trabalho_semana")),
                        String.valueOf(map.get("trabalho_domingo")),
                        String.valueOf(map.get("periodo"))
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
                        String.valueOf(map.get("duracao_intervalo")),
                        String.valueOf(map.get("tempo_alerta_intervalo")),
                        String.valueOf(map.get("hora_inicio_jornada")),
                        String.valueOf(map.get("hora_termino_jornada")),
                        String.valueOf(map.get("hora_saida_intervalo")),
                        String.valueOf(map.get("horas_trabalho_dia")),
                        String.valueOf(map.get("dias_trabalho_semana")),
                        String.valueOf(map.get("trabalho_domingo")),
                        String.valueOf(map.get("periodo")),
                        String.valueOf(jornadaTrabalho.getId())

                });
    }

    @Override
    public List<JornadaTrabalho> listar() {

        List<JornadaTrabalho> jornadaTrabalhoList = new ArrayList<JornadaTrabalho>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {
            JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();

            Map<String, Object> map = verificaListaCamposNulos(cv);

            jornadaTrabalho.setId(cv.getAsInteger("id"));
            jornadaTrabalho.setDuracao_intervalo((Integer)map.get("duracao_intervalo"));
            jornadaTrabalho.setTempo_alerta_intervalo((Integer)map.get("tempo_alerta_intervalo"));
            jornadaTrabalho.setHora_inicio_jornada((Date)map.get("hora_inicio_jornada"));
            jornadaTrabalho.setHora_saida_intervalo((Date)map.get("hora_saida_intervalo"));
            jornadaTrabalho.setHora_termino_jornada((Date) map.get("hora_termino_jornada"));
            jornadaTrabalho.setHoras_trabalho_dia((Double)map.get("horas_trabalho_dia"));
            jornadaTrabalho.setDias_trabalho_semana((Integer)map.get("dias_trabalho_semana"));
            jornadaTrabalho.setTrabalho_domingo((Boolean)map.get("trabalho_domingo"));
            jornadaTrabalho.setPeriodo((Periodo)map.get("periodo"));

            jornadaTrabalhoList.add(jornadaTrabalho);
        }

        return jornadaTrabalhoList;
    }

    @Override
    public JornadaTrabalho procurarPorId(Integer id) {

        JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();

        ContentValues cv = DB.byId(this.context, table, id);

        Map<String, Object> map = verificaListaCamposNulos(cv);

        jornadaTrabalho.setId(cv.getAsInteger("id"));
        jornadaTrabalho.setDuracao_intervalo((Integer)map.get("duracao_intervalo"));
        jornadaTrabalho.setTempo_alerta_intervalo((Integer)map.get("tempo_alerta_intervalo"));
        jornadaTrabalho.setHora_inicio_jornada((Date)map.get("hora_inicio_jornada"));
        jornadaTrabalho.setHora_saida_intervalo((Date)map.get("hora_saida_intervalo"));
        jornadaTrabalho.setHora_termino_jornada((Date) map.get("hora_termino_jornada"));
        jornadaTrabalho.setHoras_trabalho_dia((Double)map.get("horas_trabalho_dia"));
        jornadaTrabalho.setDias_trabalho_semana((Integer)map.get("dias_trabalho_semana"));
        jornadaTrabalho.setTrabalho_domingo((Boolean)map.get("trabalho_domingo"));
        jornadaTrabalho.setPeriodo((Periodo)map.get("periodo"));

        return jornadaTrabalho;
    }

    private Map<String, Object> verificaListaCamposNulos(ContentValues cv){

        Map<String, Object> map = new HashMap<String, Object>();

        Integer duracao_intervalo = null, tempo_alerta_intervalo = null, dias_trabalho_semana = null;
        Date hora_inicio_jornada = null, hora_saida_intervalo = null, hora_termino_jornada = null;
        Double horas_trabalho_dia = null;
        Boolean trabalho_domingo = null;
        Periodo periodo = null;

        if(cv.getAsInteger("duracao_intervalo") != null){
            duracao_intervalo = cv.getAsInteger("duracao_intervalo");
        }
        if(cv.getAsInteger("tempo_alerta_intervalo") != null){
            tempo_alerta_intervalo = cv.getAsInteger("tempo_alerta_intervalo");
        }
        if(cv.getAsInteger("hora_inicio_jornada") != null){
            hora_inicio_jornada = new Date(cv.getAsInteger("hora_inicio_jornada"));
        }
        if(cv.getAsInteger("hora_saida_intervalo") != null){
            hora_saida_intervalo = new Date(cv.getAsInteger("hora_saida_intervalo"));
        }
        if(cv.getAsInteger("hora_termino_jornada") != null){
            hora_termino_jornada = new Date(cv.getAsInteger("hora_termino_jornada"));
        }
        if(cv.getAsDouble("horas_trabalho_dia") != null){
            horas_trabalho_dia = cv.getAsDouble("horas_trabalho_dia");
        }
        if(cv.getAsInteger("dias_trabalho_semana") != null){
            dias_trabalho_semana = cv.getAsInteger("dias_trabalho_semana");
        }
        if(cv.getAsBoolean("trabalho_domingo") != null){
            trabalho_domingo = cv.getAsBoolean("trabalho_domingo");
        }
        if(Periodo.values()[cv.getAsInteger("periodo_trabalho")] != null){
            periodo = Periodo.values()[cv.getAsInteger("periodo_trabalho")];
        }

        map.put("duracao_intervalo", duracao_intervalo);
        map.put("tempo_alerta_intervalo", tempo_alerta_intervalo);
        map.put("hora_inicio_jornada", hora_inicio_jornada);
        map.put("hora_termino_jornada", hora_termino_jornada);
        map.put("hora_saida_intervalo", hora_saida_intervalo);
        map.put("horas_trabalho_dia", horas_trabalho_dia);
        map.put("dias_trabalho_semana", dias_trabalho_semana);
        map.put("trabalho_domingo", trabalho_domingo);
        map.put("periodo", periodo);

        return map;
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
        if(jornadaTrabalho.getHora_termino_jornada() != null){
            hora_termino_jornada = jornadaTrabalho.getHora_termino_jornada().getTime();
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
        map.put("hora_termino_jornada", hora_termino_jornada);
        map.put("hora_saida_intervalo", hora_saida_intervalo);
        map.put("horas_trabalho_dia", horas_trabalho_dia);
        map.put("dias_trabalho_semana", dias_trabalho_semana);
        map.put("trabalho_domingo", trabalho_domingo);
        map.put("periodo", periodo);

        return map;

    }
}
