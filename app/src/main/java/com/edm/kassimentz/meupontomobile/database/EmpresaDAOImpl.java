package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EmpresaDAOImpl implements EmpresaDAO {

    private Context context;

    private static final String table = "empresa";

    private long lastEmpresaID;
    private long lastTelefoneID;
    private long lastEnderecoID;


    EnderecoDAOImpl endDao;
    TelefoneDAOImpl telDao;

    public EmpresaDAOImpl(Context ctx){
        this.context = ctx;
        endDao = new EnderecoDAOImpl(this.context);
        telDao = new TelefoneDAOImpl(this.context);
    }

    @Override
    public void salvar(Empresa empresa) {

        endDao.salvar(empresa.getEndereco());
        this.setLastEnderecoID(DB.lastId(this.context, "endereco"));


        DB.executeSQL(this.context,
                "INSERT INTO " + table + " (id_endereco, nome) VALUES (?, ?)",
                new String[]{
                        String.valueOf(this.getLastEnderecoID()),
                        empresa.getNome()
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

        endDao.atualizar(empresa.getEndereco());
        List<Telefone> telefonesEmpresa = empresa.getTelefones();

        for (Telefone t: telefonesEmpresa) {
            telDao.atualizar(t);
        }

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET id_endereco = ?, nome = ? WHERE id = ?",
                new String[]{
                        String.valueOf(empresa.getEndereco().getId()),
                        empresa.getNome(),
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

        ContentValues cv = DB.byId(this.context,table, id);

        Empresa empresa = new Empresa();
        empresa.setId(cv.getAsInteger("id"));
        Endereco endereco = endDao.procurarPorId(cv.getAsInteger("id_endereco"));
        empresa.setEndereco(endereco);
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

    private long getLastEmpresaID() {
        return lastEmpresaID;
    }

    private void setLastEmpresaID(long lastEmpresaID) {
        this.lastEmpresaID = lastEmpresaID;
    }

    private long getLastTelefoneID() {
        return lastTelefoneID;
    }

    private void setLastTelefoneID(long lastTelefoneID) {
        this.lastTelefoneID = lastTelefoneID;
    }

    public long getLastEnderecoID() {
        return lastEnderecoID;
    }

    public void setLastEnderecoID(long lastEnderecoID) {
        this.lastEnderecoID = lastEnderecoID;
    }
}
