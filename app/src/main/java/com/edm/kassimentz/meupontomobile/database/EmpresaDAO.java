package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import com.edm.kassimentz.meupontomobile.model.Empresa;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface EmpresaDAO {

    boolean salvar(Empresa empresa);
    boolean deletar(Empresa empresa);
    boolean atualizar(Empresa empresa);
    List<Empresa> listar();
    Empresa procurarPorId(Integer id);
    DatabaseHandler conectar(Context context);
}
