package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;
import com.edm.kassimentz.meupontomobile.model.Empresa;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;
import com.edm.kassimentz.meupontomobile.model.JornadaTrabalho;
import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kassiane Mentz on 14/05/16.
 */
public class FuncionarioDAOImpl implements FuncionarioDAO{

    private Context context;
    String table = "funcionario";

    EnderecoDAOImpl enderecoDAO;
    TelefoneDAOImpl telefoneDAO;
    PeriodosTrabalhadosDAOImpl periodosTrabalhadosDAO;
    CalendarioJustificativasDAOImpl calendarioJustificativasDAO;
    EmpresaDAOImpl empresaDao;
    JornadaTrabalhoDAOImpl jornadaTrabalhoDao;

    private Long lastFuncionarioID;
    private Long lastEnderecoID;
    private Long lastTelefoneID;
    private Long lastPeriodosTrabalhadosID;
    private Long lastCalendarioJustificativasID;
    private Long lastEmpresaID;
    private Long lastJornadaTrabalhoID;

    public FuncionarioDAOImpl(Context ctx){
        this.context = ctx;
        enderecoDAO = new EnderecoDAOImpl(this.context);
        telefoneDAO = new TelefoneDAOImpl(this.context);
        periodosTrabalhadosDAO = new PeriodosTrabalhadosDAOImpl(this.context);
        empresaDao = new EmpresaDAOImpl(this.context);
        jornadaTrabalhoDao = new JornadaTrabalhoDAOImpl(this.context);
        calendarioJustificativasDAO = new CalendarioJustificativasDAOImpl(this.context);
    }

    @Override
    public void salvar(Funcionario funcionario) {

        if(funcionario.getEmpresa() != null){
            empresaDao.salvar(funcionario.getEmpresa());
            this.setLastEmpresaID(DB.lastId(this.context, "empresa"));
        }

        if(funcionario.getJornadaTrabalho() != null) {
            jornadaTrabalhoDao.salvar(funcionario.getJornadaTrabalho());
            this.setLastJornadaTrabalhoID(DB.lastId(this.context, "jornada_trabalho"));
        }

        Map<String, Object> map = verificaCamposNulos(funcionario);

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (nome, cpf, cargo, id_empresa, id_jornada_trabalho) VALUES (?, ?, ?, ?, ?)",
                new String[]{
                        String.valueOf(map.get("nome")),
                        String.valueOf(map.get("cpf")),
                        String.valueOf(map.get("cargo")),
                        String.valueOf(map.get("empresaID")),
                        String.valueOf(map.get("jornadaTrabalhoID"))
                });
        this.setLastFuncionarioID(DB.lastId(this.context, table));

        if(funcionario.getEnderecos() != null) {
            for (Endereco end : funcionario.getEnderecos()) {
                enderecoDAO.salvar(end);
                this.setLastEnderecoID(DB.lastId(this.context, "endereco"));

                DB.executeSQL(this.context,
                        "INSERT INTO funcionario_endereco (id_funcionario, id_endereco) VALUES (?, ?)",
                        new String[]{
                                String.valueOf(this.getLastFuncionarioID()),
                                String.valueOf(this.getLastEnderecoID())
                        });
            }
        }

        if(funcionario.getTelefones() != null) {
            for (Telefone tel : funcionario.getTelefones()) {
                telefoneDAO.salvar(tel);
                this.setLastTelefoneID(DB.lastId(this.context, "telefone"));

                DB.executeSQL(this.context,
                        "INSERT INTO funcionario_telefone (id_funcionario, id_telefone) VALUES (?, ?)",
                        new String[]{
                                String.valueOf(this.getLastFuncionarioID()),
                                String.valueOf(this.getLastTelefoneID())
                        });
            }
        }

        if(funcionario.getPeriodosTrabalhados() != null) {
            for (PeriodosTrabalhados per : funcionario.getPeriodosTrabalhados()) {
                periodosTrabalhadosDAO.salvar(per);
                this.setLastPeriodosTrabalhadosID(DB.lastId(this.context, "periodos_trabalhados"));

                DB.executeSQL(this.context,
                        "INSERT INTO funcionario_periodos_trabalhados (id_funcionario, id_periodos_trabalhados) VALUES (?, ?)",
                        new String[]{
                                String.valueOf(this.getLastFuncionarioID()),
                                String.valueOf(this.getLastPeriodosTrabalhadosID())
                        });
            }
        }

        if(funcionario.getCalendarioJustificativas() != null) {
            for (CalendarioJustificativas cal : funcionario.getCalendarioJustificativas()) {
                calendarioJustificativasDAO.salvar(cal);
                this.setLastCalendarioJustificativasID(DB.lastId(this.context, "calendario_justificativas"));

                DB.executeSQL(this.context,
                        "INSERT INTO funcionario_calendario_justificativas (id_funcionario, id_calendario_justificativas) VALUES (?, ?)",
                        new String[]{
                                String.valueOf(this.getLastFuncionarioID()),
                                String.valueOf(this.getLastCalendarioJustificativasID())
                        });
            }
        }

    }

    @Override
    public void excluir(Funcionario funcionario) {

        for (Endereco end :funcionario.getEnderecos()) {
            enderecoDAO.excluir(end);
        }

        for(Telefone tel : funcionario.getTelefones()){
            telefoneDAO.excluir(tel);
        }

        for(PeriodosTrabalhados per : funcionario.getPeriodosTrabalhados()){
            periodosTrabalhadosDAO.excluir(per);
        }

        for(CalendarioJustificativas cal : funcionario.getCalendarioJustificativas()){
            calendarioJustificativasDAO.excluir(cal);
        }

        empresaDao.excluir(funcionario.getEmpresa());
        jornadaTrabalhoDao.excluir(funcionario.getJornadaTrabalho());

        DB.executeSQL(this.context,
                "DELETE FROM funcionario_endereco WHERE id = ?",
                new String[]{
                        String.valueOf(funcionario.getId())
                });

        DB.executeSQL(this.context,
                "DELETE FROM funcionario_telefone WHERE id = ?",
                new String[]{
                        String.valueOf(funcionario.getId())
                });

        DB.executeSQL(this.context,
                "DELETE FROM funcionario_periodos_trabalhados WHERE id = ?",
                new String[]{
                        String.valueOf(funcionario.getId())
                });

        DB.executeSQL(this.context,
                "DELETE FROM funcionario_calendario_justificativas WHERE id = ?",
                new String[]{
                        String.valueOf(funcionario.getId())
                });

        DB.executeSQL(this.context,
                "DELETE FROM "+table+" WHERE id = ?",
                new String[]{
                        String.valueOf(funcionario.getId())
                });
    }

    @Override
    public void atualizar(Funcionario funcionario) {

        for (Endereco end :funcionario.getEnderecos()) {
            enderecoDAO.atualizar(end);
        }

        for(Telefone tel : funcionario.getTelefones()){
            telefoneDAO.atualizar(tel);
        }

        for(PeriodosTrabalhados per : funcionario.getPeriodosTrabalhados()){
            periodosTrabalhadosDAO.atualizar(per);
        }

        for(CalendarioJustificativas cal : funcionario.getCalendarioJustificativas()){
            calendarioJustificativasDAO.atualizar(cal);
        }

        empresaDao.atualizar(funcionario.getEmpresa());
        jornadaTrabalhoDao.atualizar(funcionario.getJornadaTrabalho());

        Map<String, Object> map = verificaCamposNulos(funcionario);

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET nome = ?, cpf = ?, cargo = ? , id_empresa = ?, id_jornada_trabalho = ? WHERE id = ?",
                new String[]{
                        String.valueOf(map.get("nome")),
                        String.valueOf(map.get("cpf")),
                        String.valueOf(map.get("cargo")),
                        String.valueOf(funcionario.getEmpresa().getId()),
                        String.valueOf(funcionario.getJornadaTrabalho().getId()),
                        String.valueOf(funcionario.getId())
                });
    }

    @Override
    public List<Endereco> getEnderecos(Integer id) {

        List<ContentValues> rows = DB.selectRows(this.context,"SELECT * FROM funcionario_endereco WHERE id_funcionario = ?", new String[]{id.toString()});
        List<Endereco> listaEnderecos = new ArrayList<Endereco>();

        for (ContentValues cv: rows) {
            Integer idEndereco = cv.getAsInteger("id_endereco");
            Endereco endereco = enderecoDAO.procurarPorId(idEndereco);
            listaEnderecos.add(endereco);
        }

        return listaEnderecos;
    }

    @Override
    public List<PeriodosTrabalhados> getPeriodosTrabalhados(Integer id) {

        List<ContentValues> rows = DB.selectRows(this.context,"SELECT * FROM funcionario_periodos_trabalhados WHERE id_funcionario = ?", new String[]{id.toString()});
        List<PeriodosTrabalhados> listaPeriodosTrabalhados = new ArrayList<PeriodosTrabalhados>();

        for(ContentValues cv : rows){
            Integer idPeriodosTrabalhados = cv.getAsInteger("id_periodos_trabalhados");
            PeriodosTrabalhados periodosTrabalhados = periodosTrabalhadosDAO.procurarPorId(idPeriodosTrabalhados);
            listaPeriodosTrabalhados.add(periodosTrabalhados);
        }

        return listaPeriodosTrabalhados;
    }

    @Override
    public List<CalendarioJustificativas> getCalendarioJustificativas(Integer id) {

        List<ContentValues> rows = DB.selectRows(this.context,"SELECT * FROM funcionario_calendario_justificativas WHERE id_funcionario = ?", new String[]{id.toString()});
        List<CalendarioJustificativas> listaCalendariosJustificativas = new ArrayList<CalendarioJustificativas>();

        for(ContentValues cv : rows){
            Integer idCalendarioJustificativas = cv.getAsInteger("id_calendario_justificativas");
            CalendarioJustificativas calendarioJustificativas = calendarioJustificativasDAO.procurarPorId(idCalendarioJustificativas);
            listaCalendariosJustificativas.add(calendarioJustificativas);
        }
        return listaCalendariosJustificativas;
    }

    @Override
    public List<Telefone> getTelefones(Integer id) {

        List<ContentValues> rows = DB.selectRows(this.context,"SELECT * FROM funcionario_telefone WHERE id_funcionario = ?", new String[]{id.toString()});
        List<Telefone> listaTelefones = new ArrayList<Telefone>();

        for (ContentValues cv : rows){
            Integer idTelefone = cv.getAsInteger("id_telefone");
            Telefone telefone = telefoneDAO.procurarPorId(idTelefone);
            listaTelefones.add(telefone);
        }
        return listaTelefones;
    }


    @Override
    public List<Funcionario> listar() {

        List<Funcionario> funcionarioList = new ArrayList<Funcionario>();

        List<ContentValues> rows = DB.selectRows(this.context, "SELECT * FROM "+table, new String[]{ });
        for (ContentValues cv: rows) {

            Map<String, Object> map = VerificaListaValoresNulos(cv);

            Funcionario funcionario = new Funcionario();
            funcionario.setId(cv.getAsInteger("id"));
            funcionario.setNome((String)map.get("nome"));
            funcionario.setCpf((String)map.get("cpf"));
            funcionario.setCargo((String)map.get("cargo"));
            funcionario.setEmpresa((Empresa)map.get("empresa"));
            funcionario.setJornadaTrabalho((JornadaTrabalho)map.get("jornadaTrabalho"));
            funcionario.setEnderecos(this.getEnderecos(cv.getAsInteger("id")));
            funcionario.setTelefones(this.getTelefones(cv.getAsInteger("id")));
            funcionario.setPeriodosTrabalhados(this.getPeriodosTrabalhados(cv.getAsInteger("id")));
            funcionario.setCalendarioJustificativas(this.getCalendarioJustificativas(cv.getAsInteger("id")));

            funcionarioList.add(funcionario);

        }

        return funcionarioList;
    }

    @Override
    public Funcionario procurarPorId(Integer id) {

        ContentValues cv = DB.byId(this.context, table, id);

        Map<String, Object> map = VerificaListaValoresNulos(cv);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(cv.getAsInteger("id"));
        funcionario.setNome((String)map.get("nome"));
        funcionario.setCpf((String)map.get("cpf"));
        funcionario.setCargo((String)map.get("cargo"));
        funcionario.setEmpresa((Empresa)map.get("empresa"));
        funcionario.setJornadaTrabalho((JornadaTrabalho)map.get("jornadaTrabalho"));
        funcionario.setEnderecos(this.getEnderecos(cv.getAsInteger("id")));
        funcionario.setTelefones(this.getTelefones(cv.getAsInteger("id")));
        funcionario.setPeriodosTrabalhados(this.getPeriodosTrabalhados(cv.getAsInteger("id")));
        funcionario.setCalendarioJustificativas(this.getCalendarioJustificativas(cv.getAsInteger("id")));

        return funcionario;
    }

    @Override
    public boolean login(String login, String senha) {

        ContentValues cv = DB.selectRow(this.context, "SELECT * FROM funcionario WHERE login = ? AND senha = ?", new String[]{login, senha});

        if(cv.getAsInteger("id") != null){
            Map<String, Object> map = VerificaListaValoresNulos(cv);

            Funcionario funcionario = new Funcionario();
            funcionario.setId(cv.getAsInteger("id"));
            funcionario.setNome((String)map.get("nome"));
            funcionario.setCpf((String)map.get("cpf"));
            funcionario.setCargo((String)map.get("cargo"));
            funcionario.setEmpresa((Empresa)map.get("empresa"));
            funcionario.setJornadaTrabalho((JornadaTrabalho)map.get("jornadaTrabalho"));
            funcionario.setEnderecos(this.getEnderecos(cv.getAsInteger("id")));
            funcionario.setTelefones(this.getTelefones(cv.getAsInteger("id")));
            funcionario.setPeriodosTrabalhados(this.getPeriodosTrabalhados(cv.getAsInteger("id")));
            funcionario.setCalendarioJustificativas(this.getCalendarioJustificativas(cv.getAsInteger("id")));

            return true;
        }else{
            return false;
        }

    }


    @Override
    public void baterPonto(Calendar hora) {

    }

    @Override
    public void emitirRelatorio() {

    }

    @Override
    public void fecharPeriodo(int periodo) {

    }



    public Long getLastFuncionarioID() {
        return lastFuncionarioID;
    }

    public void setLastFuncionarioID(Long lastFuncionarioID) {
        this.lastFuncionarioID = lastFuncionarioID;
    }

    public Long getLastEnderecoID() {
        return lastEnderecoID;
    }

    public void setLastEnderecoID(Long lastEnderecoID) {
        this.lastEnderecoID = lastEnderecoID;
    }

    public Long getLastTelefoneID() {
        return lastTelefoneID;
    }

    public void setLastTelefoneID(Long lastTelefoneID) {
        this.lastTelefoneID = lastTelefoneID;
    }

    public Long getLastPeriodosTrabalhadosID() {
        return lastPeriodosTrabalhadosID;
    }

    public void setLastPeriodosTrabalhadosID(Long lastPeriodosTrabalhadosID) {
        this.lastPeriodosTrabalhadosID = lastPeriodosTrabalhadosID;
    }

    public Long getLastCalendarioJustificativasID() {
        return lastCalendarioJustificativasID;
    }

    public void setLastCalendarioJustificativasID(Long lastCalendarioJustificativasID) {
        this.lastCalendarioJustificativasID = lastCalendarioJustificativasID;
    }

    public Long getLastEmpresaID() {
        return lastEmpresaID;
    }

    public void setLastEmpresaID(Long lastEmpresaID) {
        this.lastEmpresaID = lastEmpresaID;
    }

    public Long getLastJornadaTrabalhoID() {
        return lastJornadaTrabalhoID;
    }

    public void setLastJornadaTrabalhoID(Long lastJornadaTrabalhoID) {
        this.lastJornadaTrabalhoID = lastJornadaTrabalhoID;
    }


    private Map<String, Object> verificaCamposNulos(Funcionario funcionario) {
        Map<String, Object> map = new HashMap<String, Object>();
        String nome = null, cpf = null, cargo = null;
        Long empresaID = null, jornadaTrabalhoID = null;

        if(funcionario.getNome() != null){
            nome = funcionario.getNome();
        }
        if(funcionario.getCpf() != null){
            cpf = funcionario.getCpf();
        }
        if(funcionario.getCargo() != null){
            cargo = funcionario.getCargo();
        }
        if(this.getLastEmpresaID() != null){
            empresaID = this.getLastEmpresaID();
        }
        if(this.getLastJornadaTrabalhoID() != null){
            jornadaTrabalhoID = this.getLastJornadaTrabalhoID();
        }

        map.put("nome", nome);
        map.put("cpf", cpf);
        map.put("cargo", cargo);
        map.put("empresaID", empresaID);
        map.put("jornadaTrabalhoID", jornadaTrabalhoID);

        return map;
    }

    private Map<String, Object> VerificaListaValoresNulos(ContentValues cv) {
        Map<String, Object> map = new HashMap<String, Object>();
        String nome = null, cpf = null, cargo = null;
        Empresa empresa = null;
        JornadaTrabalho jornadaTrabalho = null;

        if(cv.getAsString("nome") != nome){
            nome = cv.getAsString("nome");
        }
        if(cv.getAsString("cpf") != null){
            cpf = cv.getAsString("cpf");
        }
        if(cv.getAsString("cargo") != null){
            cargo = cv.getAsString("cargo");
        }
        if(cv.getAsInteger("id_empresa") != null){
            empresa = empresaDao.procurarPorId(cv.getAsInteger("id_empresa"));
        }
        if(cv.getAsInteger("id_jornada_trabalho") != null){
            jornadaTrabalho = jornadaTrabalhoDao.procurarPorId(cv.getAsInteger("id_jornada_trabalho"));
        }

        map.put("nome", nome);
        map.put("cpf", cpf);
        map.put("cargo", cargo);
        map.put("empresa", empresa);
        map.put("jornadaTrabalho", jornadaTrabalho);
        return map;
    }
}
