package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EmpresaDAOImpl implements EmpresaDAO {

    private Context context;

    private static final String table = "empresa";

    private Long lastEmpresaID;
    private Long lastTelefoneID;
    private Long lastEnderecoID;


    EnderecoDAOImpl endDao;
    TelefoneDAOImpl telDao;

    public EmpresaDAOImpl(Context ctx){
        this.context = ctx;
        endDao = new EnderecoDAOImpl(this.context);
        telDao = new TelefoneDAOImpl(this.context);
    }

    @Override
    public void salvar(Empresa empresa) {

        Map<String, Object> map = VerificaCamposNulos(empresa);

        endDao.salvar(empresa.getEndereco());
        this.setLastEnderecoID(DB.lastId(this.context, "endereco"));


        DB.executeSQL(this.context,
                "INSERT INTO " + table + " (id_endereco, nome) VALUES (?, ?)",
                new String[]{
                        String.valueOf(map.get("lastEnderecoID")),
                        String.valueOf(map.get("nome"))
                });

        this.setLastEmpresaID(DB.lastId(this.context, "empresa"));

        if(empresa.getTelefones() != null) {
            for (Telefone t : empresa.getTelefones()) {

                telDao.salvar(t);
                this.setLastTelefoneID(DB.lastId(this.context, "telefone"));

                DB.executeSQL(this.context,
                        "INSERT INTO empresa_telefones (id_empresa, id_telefone) VALUES (?, ?)",
                        new String[]{
                                String.valueOf(this.getLastEmpresaID()),
                                String.valueOf(this.getLastTelefoneID())
                        });
            }
        }

    }


    @Override
    public void excluir(Empresa empresa) {

        endDao.excluir(empresa.getEndereco());
        List<Telefone> telefonesEmpresa = empresa.getTelefones();

        for (Telefone t: telefonesEmpresa) {
            telDao.excluir(t);
        }

        DB.executeSQL(this.context,
                "DELETE FROM empresa_telefones WHERE id = ?",
                new String[]{
                        String.valueOf(empresa.getId())
                });

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id = ?",
                new String[]{
                        String.valueOf(empresa.getId())
                });
    }

    @Override
    public void atualizar(Empresa empresa) {

        Map<String, Object> map = VerificaCamposNulos(empresa);

        endDao.atualizar(empresa.getEndereco());
        List<Telefone> telefonesEmpresa = empresa.getTelefones();

        for (Telefone t: telefonesEmpresa) {
            telDao.atualizar(t);
        }

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET id_endereco = ?, nome = ? WHERE id = ?",
                new String[]{
                        String.valueOf(empresa.getEndereco().getId()),
                        String.valueOf(map.get("nome")),
                        String.valueOf(empresa.getId())
                });
    }

    @Override
    public List<Empresa> listar() {

        List<Empresa> empresaLista = new ArrayList<Empresa>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {

            Empresa empresa = new Empresa();
            empresa.setId(cv.getAsInteger("id"));
            Endereco endereco = endDao.procurarPorId(cv.getAsInteger("id_endereco"));
            empresa.setEndereco(endereco);
            empresa.setTelefones(this.getTelefones(Integer.valueOf("id_empresa")));

            empresaLista.add(empresa);

        }
        return empresaLista;
    }

    @Override
    public Empresa procurarPorId(Integer id) {

        Empresa empresa = new Empresa();

        ContentValues cv = DB.byId(this.context,table, id);

        Map<String, Object> map = verificaListaCamposNulos(cv);

        empresa.setId(cv.getAsInteger("id"));
        empresa.setNome((String)map.get("nome"));
        empresa.setEndereco((Endereco) map.get("endereco"));
        empresa.setTelefones(this.getTelefones(id));

        return empresa;
    }


    @Override
    public List<Telefone> getTelefones(Integer id) {

        List<ContentValues> rows = DB.selectRows(this.context,"SELECT * FROM empresa_telefones WHERE id_empresa = ?", new String[]{id.toString()});
        List<Telefone> telefonesList = new ArrayList<Telefone>();
        for (ContentValues cv: rows) {
            Integer idTelefone = cv.getAsInteger("id_telefone");
            Telefone telefone = telDao.procurarPorId(idTelefone);
            telefonesList.add(telefone);
        }
        return telefonesList;
    }

    private Long getLastEmpresaID() {
        return lastEmpresaID;
    }

    private void setLastEmpresaID(Long lastEmpresaID) {
        this.lastEmpresaID = lastEmpresaID;
    }

    private Long getLastTelefoneID() {
        return lastTelefoneID;
    }

    private void setLastTelefoneID(Long lastTelefoneID) {
        this.lastTelefoneID = lastTelefoneID;
    }

    public Long getLastEnderecoID() {
        return lastEnderecoID;
    }

    public void setLastEnderecoID(Long lastEnderecoID) {
        this.lastEnderecoID = lastEnderecoID;
    }


    private Map<String, Object> VerificaCamposNulos(Empresa empresa) {
        Map<String, Object> map = new HashMap<String, Object>();
        String nome = null;
        Long lastEnderecoID = null;

        if(this.getLastEnderecoID() != null){
            lastEnderecoID = this.getLastEnderecoID();
        }
        if(empresa.getNome() != null){
            nome = empresa.getNome();
        }

        map.put("lastEnderecoID", lastEnderecoID);
        map.put("nome", nome);
        return map;
    }

    private Map<String, Object> verificaListaCamposNulos(ContentValues cv) {
        Map<String, Object> map = new HashMap<String, Object>();
        String nome = null;
        Endereco endereco = null;

        if(cv.getAsString("nome") != null){
            nome = cv.getAsString("nome");
        }
        if(cv.getAsInteger("id_endereco") != null){
            endereco = endDao.procurarPorId(cv.getAsInteger("id_endereco"));
        }

        map.put("nome", nome);
        map.put("endereco", endereco);
        return map;
    }
}
