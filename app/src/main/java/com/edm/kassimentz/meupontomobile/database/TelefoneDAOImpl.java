package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.List;

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

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (ddd, telefone) VALUES (?, ?)",
                new String[]{
                        String.valueOf(telefone.getDdd()),
                        String.valueOf(telefone.getNumero())
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

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET ddd = ?, telefone = ? WHERE id = ?",
                new String[]{
                        telefone.getDdd(),
                        telefone.getNumero(),
                        String.valueOf(telefone.getId())
                });
    }

    @Override
    public List<Telefone> listar() {

        List<Telefone> telefones = new ArrayList<Telefone>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {
            Telefone tel = new Telefone();
            tel.setId(cv.getAsInteger("id"));
            tel.setDdd(cv.getAsString("ddd"));
            tel.setNumero(cv.getAsString("numero"));

            telefones.add(tel);
        }

        return telefones;
    }

    @Override
    public Telefone procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table, id);

        Telefone tel = new Telefone();

        tel.setId(cv.getAsInteger("id"));
        tel.setDdd(cv.getAsString("ddd"));
        tel.setNumero(cv.getAsString("numero"));

        return tel;
    }
}
