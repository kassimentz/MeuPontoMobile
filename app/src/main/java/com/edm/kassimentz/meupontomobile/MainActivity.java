package com.edm.kassimentz.meupontomobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.edm.kassimentz.meupontomobile.database.FuncionarioDAOImpl;
import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Endereco enderecoEmpresa = new Endereco();
        enderecoEmpresa.setCep(90820030);
        enderecoEmpresa.setCidade("Porto Alegre");
        enderecoEmpresa.setComplemento("Loja 2");
        enderecoEmpresa.setEstado("RS");
        enderecoEmpresa.setLogradouro("Rua A");
        enderecoEmpresa.setNumero(200);
        enderecoEmpresa.setPais("BR");


        Telefone telefoneEmpresa = new Telefone();
        telefoneEmpresa.setNumero(32323232);
        telefoneEmpresa.setDdd(51);

        List<Telefone> telefones = new ArrayList<Telefone>();
        telefones.add(telefoneEmpresa);

        Empresa empresa = new Empresa();
        empresa.setNome("Empresa 1");
        empresa.setEndereco(enderecoEmpresa);
        empresa.setTelefone(telefones);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Kassiane Mentz");
        funcionario.setCargo("Desenvolvedora");
        funcionario.setCpf("01119475023");
        funcionario.setEmpresa(empresa);

        FuncionarioDAOImpl funcionarioDAO = new FuncionarioDAOImpl(this.getBaseContext());
        funcionarioDAO.insertFuncionario(funcionario);

        Log.d("table", "funcionario inserido com sucesso");

        List<Funcionario> listFuncionarios = funcionarioDAO.buscarTodos();

        for (Funcionario f: listFuncionarios) {
            Log.d("table", f.getNome());
            Log.d("table", f.getCargo());
        }

    }
}
