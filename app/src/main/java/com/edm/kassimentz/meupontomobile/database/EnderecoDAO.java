package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.Endereco;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface EnderecoDAO extends GenericDAO<Endereco>{

    public Endereco buscaEndereco(int cep);
}
