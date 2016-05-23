package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PeriodosTrabalhadosDAOImpl implements PeriodosTrabalhadosDAO {

    private Context context;
    String table = "periodos_trabalhados";

    public PeriodosTrabalhadosDAOImpl(Context ctx){
        this.context = ctx;
    }

    /*TODO*/
    @Override
    public List<PeriodosTrabalhados> getPeriodosTrabalhadosPorData(Calendar inicio, Calendar fim) {
        return null;
    }

    @Override
    public void salvar(PeriodosTrabalhados periodosTrabalhados) {

        Map<String, Object> map = verificaCamposNulos(periodosTrabalhados);

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (data_hora_inicio, data_hora_fim) VALUES (?, ?)",
                new String[]{
                        String.valueOf(map.get("data_hora_inicio")),
                        String.valueOf(map.get("data_hora_fim"))
                });
    }

    @Override
    public void excluir(PeriodosTrabalhados periodosTrabalhados) {

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id = ?",
                new String[]{
                        String.valueOf(periodosTrabalhados.getId())
                });
    }

    @Override
    public void atualizar(PeriodosTrabalhados periodosTrabalhados) {

        Map<String, Object> map = verificaCamposNulos(periodosTrabalhados);

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET data_hora_inicio = ?, data_hora_fim = ? WHERE id = ?",
                new String[]{
                        String.valueOf(map.get("data_hora_inicio")),
                        String.valueOf(map.get("data_hora_fim")),
                        String.valueOf(periodosTrabalhados.getId())
                });
    }

    @Override
    public List<PeriodosTrabalhados> listar() {

        List<PeriodosTrabalhados> periodosTrabalhadosList = new ArrayList<PeriodosTrabalhados>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {

            Map<String, Object> map = verificaListaCamposNulos(cv);

            PeriodosTrabalhados periodosTrabalhados = new PeriodosTrabalhados();

            periodosTrabalhados.setId(cv.getAsInteger("id"));
            periodosTrabalhados.setData_hora_inicio((Date)map.get("data_hora_inicio"));
            periodosTrabalhados.setData_hora_fim((Date)map.get("data_hora_fim"));
            periodosTrabalhadosList.add(periodosTrabalhados);
        }

        return periodosTrabalhadosList;
    }

    @Override
    public PeriodosTrabalhados procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table, id);

        Map<String, Object> map = verificaListaCamposNulos(cv);

        PeriodosTrabalhados periodosTrabalhados = new PeriodosTrabalhados();

        periodosTrabalhados.setId(cv.getAsInteger("id"));
        periodosTrabalhados.setData_hora_inicio((Date)map.get("data_hora_inicio"));
        periodosTrabalhados.setData_hora_fim((Date)map.get("data_hora_fim"));

        return periodosTrabalhados;
    }

    private Map<String, Object> verificaListaCamposNulos(ContentValues cv) {
        Map<String, Object> map = new HashMap<String, Object>();
        Date data_hora_inicio = null, data_hora_fim = null;

        if(cv.getAsLong("data_hora_inicio") != null){
            data_hora_inicio = new Date(cv.getAsLong("data_hora_inicio"));
        }
        if(cv.getAsLong("data_hora_fim") != null){
            data_hora_fim = new Date(cv.getAsLong("data_hora_fim"));
        }

        map.put("data_hora_inicio", data_hora_inicio);
        map.put("data_hora_fim", data_hora_fim);
        return map;
    }

    private Map<String, Object> verificaCamposNulos(PeriodosTrabalhados periodosTrabalhados){

        Map<String, Object> map = new HashMap<String, Object>();
        Long data_hora_inicio = null, data_hora_fim = null;

        if(periodosTrabalhados.getData_hora_inicio() != null){
            data_hora_inicio = periodosTrabalhados.getData_hora_inicio().getTime();
        }

        if(periodosTrabalhados.getData_hora_fim() != null){
            data_hora_fim = periodosTrabalhados.getData_hora_fim().getTime();
        }

        map.put("data_hora_inicio", data_hora_inicio);
        map.put("data_hora_fim", data_hora_fim);

        return map;
    }
}
