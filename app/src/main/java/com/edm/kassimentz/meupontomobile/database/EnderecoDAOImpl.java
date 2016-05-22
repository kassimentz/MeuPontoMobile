package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.Endereco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EnderecoDAOImpl implements EnderecoDAO {

    private Context context;
    private static final String table = "endereco";

    public EnderecoDAOImpl(Context ctx){
        this.context = ctx;
    }


    @Override
    public void salvar(Endereco endereco) {

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (logradouro, cep, numero, complemento, cidade, estado, pais) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new String[]{
                        endereco.getLogradouro(),
                        String.valueOf(endereco.getCep()),
                        String.valueOf(endereco.getNumero()),
                        endereco.getComplemento(),
                        endereco.getCidade(),
                        endereco.getEstado(),
                        endereco.getPais()
                });
    }

    @Override
    public void excluir(Endereco endereco) {

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id = ?",
                new String[]{
                        String.valueOf(endereco.getId())
                });
    }

    @Override
    public void atualizar(Endereco endereco) {

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET logradouro = ?, cep = ?, numero = ?, complemento = ?, cidade = ?, estado = ?, pais = ? WHERE id = ?",
                new String[]{
                        endereco.getLogradouro(),
                        String.valueOf(endereco.getCep()),
                        String.valueOf(endereco.getNumero()),
                        endereco.getComplemento(),
                        endereco.getCidade(),
                        endereco.getEstado(),
                        endereco.getPais(),
                        String.valueOf(endereco.getId())
                });
    }

    @Override
    public List<Endereco> listar() {

        List<Endereco> enderecosList = new ArrayList<Endereco>();
        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });

        for (ContentValues cv: rows){

            Endereco end = new Endereco();
            end.setId(cv.getAsInteger("id"));
            end.setLogradouro(cv.getAsString("logradouro"));
            end.setCep(cv.getAsInteger("cep"));
            end.setNumero(cv.getAsInteger("numero"));
            end.setComplemento(cv.getAsString("complemento"));
            end.setCidade(cv.getAsString("cidade"));
            end.setEstado(cv.getAsString("estado"));
            end.setPais(cv.getAsString("pais"));

            enderecosList.add(end);
        }

        return enderecosList;
    }

    @Override
    public Endereco procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table, id);

        Endereco end = new Endereco();
        end.setId(cv.getAsInteger("id"));
        end.setLogradouro(cv.getAsString("logradouro"));
        end.setCep(cv.getAsInteger("cep"));
        end.setNumero(cv.getAsInteger("numero"));
        end.setComplemento(cv.getAsString("complemento"));
        end.setCidade(cv.getAsString("cidade"));
        end.setEstado(cv.getAsString("estado"));
        end.setPais(cv.getAsString("pais"));

        return end;
    }


    /**
     * TODO
     */
    @Override
    public Endereco buscaEndereco(int cep) {
        return null;
    }
}
