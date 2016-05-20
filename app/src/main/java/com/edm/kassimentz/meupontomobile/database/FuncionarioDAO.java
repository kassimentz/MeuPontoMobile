package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;

import com.edm.kassimentz.meupontomobile.model.Funcionario;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface FuncionarioDAO{


    boolean salvar(Funcionario funcionario);
    boolean deletar(Funcionario funcionario);
    boolean atualizar(Funcionario funcionario);
    List<Funcionario> listar();
    Funcionario procurarPorId(Integer id);
    DatabaseHandler conectar(Context context);

    void baterPonto(Calendar hora);
    void emitirRelatorio();
    void fecharPeriodo(int periodo);
}
