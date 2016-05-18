package com.edm.kassimentz.meupontomobile.database;

import com.edm.kassimentz.meupontomobile.model.Endereco;

import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EnderecoDAOImpl extends DaoDB<Endereco> implements EnderecoDAO {
    @Override
    public boolean salvar(Endereco endereco) {
        return false;
    }

    @Override
    public boolean deleter(Endereco endereco) {
        return false;
    }

    @Override
    public boolean atualizar(Endereco endereco) {
        return false;
    }

    @Override
    public List<Endereco> listar() {
        return null;
    }

    @Override
    public Endereco procurarPorId(Integer id) {
        return null;
    }

    @Override
    public Endereco buscaEndereco(int cep) {
        return null;
    }
}
