package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface TelefoneDAO {

    boolean salvar(Telefone telefone);
    boolean deletar(Telefone telefone);
    boolean atualizar(Telefone telefone);
    List<Telefone> listar();
    Telefone procurarPorId(Integer id);
    DatabaseHandler conectar(Context context);
}
