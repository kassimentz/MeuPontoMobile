package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.Endereco;

/**
 * Created by Cassio on 14/05/16.
 */
public interface EnderecoDAO {

    public Endereco buscaEndereco(int cep);
}
