package com.edm.kassimentz.meupontomobile;

import android.content.Intent;
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
import com.edm.kassimentz.meupontomobile.model.Periodo;
import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FuncionarioDAOImpl funcionarioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void cadastrar (View v){
        Intent cadastro =  new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(cadastro);
    }

    public void salvar (View v){

        /*funcionarioDAO = new FuncionarioDAOImpl(this.getBaseContext());

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

        Endereco endereco2 = new Endereco();
        endereco2.setPais("BR");
        endereco2.setEstado("RS");
        endereco2.setCidade("Porto Alegre");
        endereco2.setComplemento("Apto 101");
        endereco2.setCep(90820030);
        endereco2.setNumero(150);
        endereco2.setLogradouro("Rua diomario moojen");

        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);
        enderecos.add(endereco2);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Kassiane silva");
        funcionario.setCargo("programadora");
        funcionario.setCpf("01119475023");
        funcionario.setTelefones(list);
        funcionario.setEnderecos(enderecos);

        Calendar hoje = Calendar.getInstance();
        Date now = hoje.getTime();

        CalendarioJustificativas cal = new CalendarioJustificativas();
        cal.setData_hora(now);
        cal.setJustificativa(Justificativa.ATESTADO_MEDICO);
        cal.setObservacao("fsafafare");

        List<CalendarioJustificativas> calendarios = new ArrayList<CalendarioJustificativas>();
        calendarios.add(cal);


        funcionario.setCalendarioJustificativas(calendarios);
        PeriodosTrabalhados per = new PeriodosTrabalhados();
        per.setData_hora_fim(now);
        per.setData_hora_inicio(now);

        List<PeriodosTrabalhados> listPer = new ArrayList<PeriodosTrabalhados>();
        listPer.add(per);

        funcionario.setPeriodosTrabalhados(listPer);

        JornadaTrabalho jor = new JornadaTrabalho();
        jor.setTrabalho_domingo(true);
        jor.setPeriodo(Periodo.DIURNO);
        funcionario.setJornadaTrabalho(jor);

        Empresa empresa = new Empresa();
        empresa.setNome("nova empresa");
        empresa.setEndereco(endereco2);
        empresa.setTelefones(list);

        funcionario.setEmpresa(empresa);
        funcionarioDAO.salvar(funcionario);

        List<Funcionario> listFuncionarios = funcionarioDAO.listar();

        for (Funcionario f: listFuncionarios) {
            Log.d("table", f.getNome());
            Log.d("table", f.getCargo());
            for (Endereco e: f.getEnderecos()) {
                Log.d("table", e.getLogradouro());
            }

        }*/
    }
}
