package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.Funcionario;

import java.util.Calendar;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface FuncionarioDAO{

    public void baterPonto(Calendar hora);
    public void emitirRelatorio();
    public void fecharPeriodo(int periodo);
}
