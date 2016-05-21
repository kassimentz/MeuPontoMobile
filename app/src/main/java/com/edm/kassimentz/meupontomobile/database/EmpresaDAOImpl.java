package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class EmpresaDAOImpl extends EmpresaDAOBase {

    DatabaseHandler databaseHandler;
    String tabela = "empresa";
    String tabela2 = "empresa_telefones";

    public EmpresaDAOImpl(Context context){
        databaseHandler = conectar(context);
    }

    @Override
    public boolean salvar(Empresa empresa) {

        SQLiteDatabase banco = databaseHandler.getWritableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("nome", empresa.getNome());
        dados.put("id_endereco", empresa.getEndereco().getId());

        boolean result = banco.insert(tabela, null, dados) > 0;


        Integer idEmpresa = empresa.getId();
        List<Telefone> telefones = empresa.getTelefones();
        ContentValues dadosTelefone;
        boolean resultFone = false;

        for (Telefone t: telefones) {
            dadosTelefone = new ContentValues();
            dadosTelefone.put("id_empresa", idEmpresa);
            dadosTelefone.put("id_telefone", t.getId());

            resultFone = banco.insert(tabela2, null, dadosTelefone) > 0;
        }

        banco.close();
        Log.d(tabela,tabela+ " inserido com sucesso");
        Log.d(tabela,tabela2+" inserido com sucesso");

        return(result && resultFone);
    }

    @Override
    public boolean deletar(Empresa empresa) {

        //verificar como deletar os telefones relacionados
        SQLiteDatabase banco = databaseHandler.getWritableDatabase();
        boolean result = banco.delete(tabela, "id_empresa=?",
                new String[]{empresa.getId().toString()}) > 0;
        banco.close();
        return(result);
    }

    @Override
    public boolean atualizar(Empresa empresa) {

        SQLiteDatabase banco = databaseHandler.getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", empresa.getNome());
        dados.put("id_endereco", empresa.getEndereco().getId());

        boolean result = banco.update(tabela, dados, "id_empresa=?",
                new String[]{empresa.getId().toString()}) > 0;
        banco.close();
        return (result);
    }

    @Override
    public List<Empresa> listar() {

        List<Empresa> funcionarios = new ArrayList<Empresa>();
        SQLiteDatabase banco = databaseHandler.getReadableDatabase();
        String[] colunas = {"nome","cpf","cargo"};
        Cursor c = banco.query(tabela,colunas,
                null, null, null, null, null);

        while(c.moveToNext()) {
            Endereco endereco = new Endereco();
            //fazer o procurar endereco por id primeiro

            Empresa e = new Empresa();
            e.setNome(c.getString(c.getColumnIndex("nome")));


            funcionarios.add(e);
        }
        return funcionarios;
    }

    @Override
    public Empresa procurarPorId(Integer id) {
        return null;
    }
}
