package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.Endereco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, Object> map = verificaCamposNulos(endereco);

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (logradouro, cep, numero, complemento, cidade, estado, pais) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new String[]{
                        String.valueOf(map.get("logradouro")),
                        String.valueOf(map.get("cep")),
                        String.valueOf(map.get("numero")),
                        String.valueOf(map.get("complemento")),
                        String.valueOf(map.get("cidade")),
                        String.valueOf(map.get("estado")),
                        String.valueOf(map.get("pais"))
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

        Map<String, Object> map = verificaCamposNulos(endereco);

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET logradouro = ?, cep = ?, numero = ?, complemento = ?, cidade = ?, estado = ?, pais = ? WHERE id = ?",
                new String[]{
                        String.valueOf(map.get("logradouro")),
                        String.valueOf(map.get("cep")),
                        String.valueOf(map.get("numero")),
                        String.valueOf(map.get("complemento")),
                        String.valueOf(map.get("cidade")),
                        String.valueOf(map.get("estado")),
                        String.valueOf(map.get("pais")),
                        String.valueOf(endereco.getId())
                });
    }

    @Override
    public List<Endereco> listar() {

        List<Endereco> enderecosList = new ArrayList<Endereco>();
        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });

        for (ContentValues cv: rows){

            Map<String, Object> map = verificaListaCamposNulos(cv);

            Endereco end = new Endereco();
            end.setId(cv.getAsInteger("id"));
            end.setLogradouro((String)map.get("logradouro"));
            end.setCep((Integer)map.get("cep"));
            end.setNumero((Integer)map.get("numero"));
            end.setComplemento((String)map.get("complemento"));
            end.setCidade((String)map.get("cidade"));
            end.setEstado((String)map.get("estado"));
            end.setPais((String)map.get("pais"));

            enderecosList.add(end);
        }

        return enderecosList;
    }

    @Override
    public Endereco procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table, id);

        Map<String, Object> map = verificaListaCamposNulos(cv);

        Endereco end = new Endereco();
        end.setId(cv.getAsInteger("id"));
        end.setLogradouro((String)map.get("logradouro"));
        end.setCep((Integer)map.get("cep"));
        end.setNumero((Integer)map.get("numero"));
        end.setComplemento((String)map.get("complemento"));
        end.setCidade((String)map.get("cidade"));
        end.setEstado((String)map.get("estado"));
        end.setPais((String)map.get("pais"));

        return end;
    }


    /**
     * TODO
     */
    @Override
    public Endereco buscaEndereco(int cep) {
        return null;
    }

    private Map<String, Object> verificaCamposNulos(Endereco endereco) {

        Map<String, Object> map = new HashMap<String, Object>();
        Integer numero = null, cep = null;
        String logradouro = null, complemento = null, cidade = null, estado = null, pais = null;

        if(endereco.getLogradouro() != null){
            logradouro = endereco.getLogradouro();
        }
        if(endereco.getCep() != null){
            cep = endereco.getCep();
        }
        if(endereco.getNumero() != null){
            numero = endereco.getNumero();
        }
        if(endereco.getComplemento() != null){
            complemento = endereco.getComplemento();
        }
        if(endereco.getCidade() != null){
            cidade = endereco.getCidade();
        }
        if(endereco.getEstado() != null){
            estado = endereco.getEstado();
        }
        if(endereco.getPais() != null){
            pais = endereco.getPais();
        }

        map.put("logradouro", logradouro);
        map.put("cep", cep);
        map.put("numero", numero);
        map.put("complemento", complemento);
        map.put("cidade", cidade);
        map.put("estado", estado);
        map.put("pais", pais);

        return map;
    }


    private Map<String, Object> verificaListaCamposNulos(ContentValues cv) {

        Map<String, Object> map = new HashMap<String, Object>();
        Integer numero = null, cep = null;
        String logradouro = null, complemento = null, cidade = null, estado = null, pais = null;

        if(cv.getAsString("logradouro") != null){
            logradouro = cv.getAsString("logradouro");
        }
        if(cv.getAsInteger("cep") != null){
            cep = cv.getAsInteger("cep");
        }
        if(cv.getAsInteger("numero") != null){
            numero = cv.getAsInteger("numero");
        }
        if(cv.getAsString("complemento") != null){
            complemento = cv.getAsString("complemento");
        }
        if(cv.getAsString("cidade") != null){
            cidade = cv.getAsString("cidade");
        }
        if(cv.getAsString("estado") != null){
            estado = cv.getAsString("estado");
        }
        if(cv.getAsString("pais") != null){
            pais = cv.getAsString("pais");
        }

        map.put("logradouro", logradouro);
        map.put("cep", cep);
        map.put("numero", numero);
        map.put("complemento", complemento);
        map.put("cidade", cidade);
        map.put("estado", estado);
        map.put("pais", pais);

        return map;
    }

}
