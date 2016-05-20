package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface EnderecoDAO {

    boolean salvar(Endereco endereco);
    boolean deletar(Endereco endereco);
    boolean atualizar(Endereco endereco);
    List<Endereco> listar();
    Endereco procurarPorId(Integer id);
    DatabaseHandler conectar(Context context);

    public Endereco buscaEndereco(int cep);
}
