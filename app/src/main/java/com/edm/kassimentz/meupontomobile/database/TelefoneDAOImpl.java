package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class TelefoneDAOImpl implements TelefoneDAO {

    private Context context;
    private static final String table = "telefone";

    public TelefoneDAOImpl(Context ctx){
        this.context = ctx;
    }

    @Override
    public void salvar(Telefone telefone) {

        Map<String, Object> map = verificaValoresNulos(telefone);

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (ddd, telefone) VALUES (?, ?)",
                new String[]{
                        String.valueOf(map.get("ddd")),
                        String.valueOf(map.get("numero"))
                });
    }


    @Override
    public void excluir(Telefone telefone) {

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id = ?",
                new String[]{
                        String.valueOf(telefone.getId())
                });
    }

    @Override
    public void atualizar(Telefone telefone) {

        Map<String, Object> map = verificaValoresNulos(telefone);

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET ddd = ?, telefone = ? WHERE id = ?",
                new String[]{
                        String.valueOf(map.get("ddd")),
                        String.valueOf(map.get("numero")),
                        String.valueOf(telefone.getId())
                });
    }

    @Override
    public List<Telefone> listar() {

        List<Telefone> telefones = new ArrayList<Telefone>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {

            Map<String, Object> map = verificaListaCamposNulos(cv);

            Telefone tel = new Telefone();
            tel.setId(cv.getAsInteger("id"));
            tel.setDdd((String)map.get("ddd"));
            tel.setNumero((String)map.get("numero"));

            telefones.add(tel);
        }

        return telefones;
    }

    @Override
    public Telefone procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table, id);

        Map<String, Object> map = verificaListaCamposNulos(cv);

        Telefone tel = new Telefone();

        tel.setId(cv.getAsInteger("id"));
        tel.setDdd((String)map.get("ddd"));
        tel.setNumero((String)map.get("numero"));

        return tel;
    }

    private Map<String, Object> verificaListaCamposNulos(ContentValues cv) {
        Map<String, Object> map = new HashMap<String, Object>();
        String ddd = null, numero = null;

        if(cv.getAsString("ddd") != null){
            ddd = cv.getAsString("ddd");
        }
        if(cv.getAsString("numero") != null){
            numero = cv.getAsString("numero");
        }

        map.put("ddd", ddd);
        map.put("numero", numero);
        return map;
    }

    private Map<String, Object> verificaValoresNulos(Telefone telefone) {
        Map<String, Object> map = new HashMap<String, Object>();
        String ddd = null, numero = null;

        if(telefone.getDdd() != null){
            ddd = telefone.getDdd();
        }
        if(telefone.getNumero() != null){
            numero = telefone.getNumero();
        }

        map.put("ddd", ddd);
        map.put("numero", numero);
        return map;
    }
}
