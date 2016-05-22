package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;
import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface FuncionarioDAO extends GenericDAO<Funcionario>{

    public List<Endereco> getEnderecos(Integer id);
    public List<PeriodosTrabalhados> getPeriodosTrabalhados(Integer id);
    public List<CalendarioJustificativas> getCalendarioJustificativas(Integer id);
    public List<Telefone> getTelefones(Integer id);

    public void baterPonto(Calendar hora);
    public void emitirRelatorio();
    public void fecharPeriodo(int periodo);
}
