package com.edm.kassimentz.meupontomobile.database;

import android.content.Context;
import com.edm.kassimentz.meupontomobile.model.JornadaTrabalho;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public interface JornadaTrabalhoDAO {

    boolean salvar(JornadaTrabalho jornadaTrabalho);
    boolean deletar(JornadaTrabalho jornadaTrabalho);
    boolean atualizar(JornadaTrabalho jornadaTrabalho);
    List<JornadaTrabalho> listar();
    JornadaTrabalho procurarPorId(Integer id);
    DatabaseHandler conectar(Context context);
}
