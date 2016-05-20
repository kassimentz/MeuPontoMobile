package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.model.JornadaTrabalho;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class JornadaTrabalhoDAOImpl extends JornadaTrabalhoDAOBase{

    DatabaseHandler databaseHandler;
    String tabela = "JornadaTrabalhoDAOImpl";

    public JornadaTrabalhoDAOImpl(Context context){
        databaseHandler = conectar(context);
    }

    @Override
    public boolean salvar(JornadaTrabalho jornadaTrabalho) {
        return false;
    }

    @Override
    public boolean deletar(JornadaTrabalho jornadaTrabalho) {
        return false;
    }

    @Override
    public boolean atualizar(JornadaTrabalho jornadaTrabalho) {
        return false;
    }

    @Override
    public List<JornadaTrabalho> listar() {
        return null;
    }

    @Override
    public JornadaTrabalho procurarPorId(Integer id) {
        return null;
    }
}
