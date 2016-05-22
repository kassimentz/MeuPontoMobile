package com.edm.kassimentz.meupontomobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.edm.kassimentz.meupontomobile.database.FuncionarioDAOImpl;
import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;
import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;
import com.edm.kassimentz.meupontomobile.model.JornadaTrabalho;
import com.edm.kassimentz.meupontomobile.model.Justificativa;
import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FuncionarioDAOImpl funcionarioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void salvar (View v){

        funcionarioDAO = new FuncionarioDAOImpl(this.getBaseContext());

        Telefone telefone = new Telefone();
        telefone.setDdd("51");
        telefone.setNumero("81115777");

        List<Telefone> list = new ArrayList<Telefone>();
        list.add(telefone);

        Endereco endereco = new Endereco();
        endereco.setPais("BR");
        endereco.setEstado("RS");
        endereco.setCidade("Porto Alegre");
        endereco.setComplemento("Apto 206 F");
        endereco.setCep(90820030);
        endereco.setNumero(560);
        endereco.setLogradouro("Rua gabriel franco da luz");

        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Kassiane Mentz");
        funcionario.setCargo("Desenvolvedora");
        funcionario.setCpf("01119475023");
        funcionario.setTelefones(list);
        funcionario.setEnderecos(enderecos);

        CalendarioJustificativas cal = new CalendarioJustificativas();
        cal.setData_hora(Calendar.getInstance());

        List<CalendarioJustificativas> calendarios = new ArrayList<CalendarioJustificativas>();
        calendarios.add(cal);

        funcionario.setCalendarioJustificativas(calendarios);
        PeriodosTrabalhados per = new PeriodosTrabalhados();


        List<PeriodosTrabalhados> listPer = new ArrayList<PeriodosTrabalhados>();
        listPer.add(per);

        funcionario.setPeriodosTrabalhados(listPer);

        JornadaTrabalho jor = new JornadaTrabalho();
        jor.setTrabalho_domingo(false);
        funcionario.setJornadaTrabalho(jor);

        Empresa empresa = new Empresa();
        empresa.setNome("teste");

        funcionario.setEmpresa(empresa);
        funcionarioDAO.salvar(funcionario);

        List<Funcionario> listFuncionarios = funcionarioDAO.listar();

        for (Funcionario f: listFuncionarios) {
            Log.d("table", f.getNome());
            Log.d("table", f.getCargo());
            for (Endereco e: f.getEnderecos()) {
                Log.d("table", e.getLogradouro());
            }

        }
    }
}
