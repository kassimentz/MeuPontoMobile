package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.Empresa;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EmpresaDAOImpl implements EmpresaDAO {

    private Context context;
    private static final String table = "empresa";

    public EmpresaDAOImpl(Context ctx){
        this.context = ctx;
    }

    @Override
    public void salvar(Empresa empresa) {
//CREATE TABLE empresa (id_empresa INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_endereco INTEGER (10) REFERENCES endereco (id_endereco), nome VARCHAR (100));";
        //inserir na tabela de telefone tambem
        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (id_endereco, nome) VALUES (?, ?)",
                new String[]{

                });
    }

    @Override
    public void excluir(Empresa empresa) {

    }

    @Override
    public void atualizar(Empresa empresa) {

    }

    @Override
    public List<Empresa> listar() {
        return null;
    }

    @Override
    public Empresa procurarPorId(Integer id) {
        return null;
    }
}
