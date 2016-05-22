package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 *
 * Se necessitar de algum metodo especifico
 */
public interface EmpresaDAO extends GenericDAO<Empresa>{

    public List<Telefone> getTelefones(Integer id);
}
