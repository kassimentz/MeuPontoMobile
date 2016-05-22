package com.edm.kassimentz.meupontomobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.edm.kassimentz.meupontomobile.database.banco.DB;
import com.edm.kassimentz.meupontomobile.model.CalendarioJustificativas;
import com.edm.kassimentz.meupontomobile.model.Endereco;
import com.edm.kassimentz.meupontomobile.model.Funcionario;
import com.edm.kassimentz.meupontomobile.model.PeriodosTrabalhados;
import com.edm.kassimentz.meupontomobile.model.Telefone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    private long lastFuncionarioID;
    private long lastEnderecoID;
    private long lastTelefoneID;
    private long lastPeriodosTrabalhadosID;
    private long lastCalendarioJustificativasID;
    private long lastEmpresaID;
    private long lastJornadaTrabalhoID;

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

        DB.executeSQL(this.context,
                "INSERT INTO "+table+" (nome, cpf, cargo, id_empresa, id_jornada_trabalho) VALUES (?, ?, ?, ?, ?)",
                new String[]{
                        funcionario.getNome(),
                        funcionario.getCpf(),
                        funcionario.getCargo(),
                        String.valueOf(this.getLastEmpresaID()),
                        String.valueOf(this.getLastJornadaTrabalhoID())
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

        DB.executeSQL(this.context,
                "UPDATE "+table+" SET nome = ?, cpf = ?, cargo = ? , id_empresa = ?, id_jornada_trabalho = ? WHERE id = ?",
                new String[]{
                        funcionario.getNome(),
                        funcionario.getCpf(),
                        funcionario.getCargo(),
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

            Funcionario funcionario = new Funcionario();
            funcionario.setId(cv.getAsInteger("id"));
            funcionario.setNome(cv.getAsString("nome"));
            funcionario.setCpf(cv.getAsString("cpf"));
            funcionario.setCargo(cv.getAsString("cargo"));
            funcionario.setEmpresa(empresaDao.procurarPorId(cv.getAsInteger("id_empresa")));
            funcionario.setJornadaTrabalho(jornadaTrabalhoDao.procurarPorId(cv.getAsInteger("id_jornada_trabalho")));
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

        Funcionario funcionario = new Funcionario();
        funcionario.setId(cv.getAsInteger("id"));
        funcionario.setNome(cv.getAsString("nome"));
        funcionario.setCpf(cv.getAsString("cpf"));
        funcionario.setCargo(cv.getAsString("cargo"));
        funcionario.setEmpresa(empresaDao.procurarPorId(cv.getAsInteger("id_empresa")));
        funcionario.setJornadaTrabalho(jornadaTrabalhoDao.procurarPorId(cv.getAsInteger("id_jornada_trabalho")));
        funcionario.setEnderecos(this.getEnderecos(cv.getAsInteger("id")));
        funcionario.setTelefones(this.getTelefones(cv.getAsInteger("id")));
        funcionario.setPeriodosTrabalhados(this.getPeriodosTrabalhados(cv.getAsInteger("id")));
        funcionario.setCalendarioJustificativas(this.getCalendarioJustificativas(cv.getAsInteger("id")));

        return funcionario;
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

    public long getLastFuncionarioID() {
        return lastFuncionarioID;
    }

    public void setLastFuncionarioID(long lastFuncionarioID) {
        this.lastFuncionarioID = lastFuncionarioID;
    }

    public long getLastEnderecoID() {
        return lastEnderecoID;
    }

    public void setLastEnderecoID(long lastEnderecoID) {
        this.lastEnderecoID = lastEnderecoID;
    }

    public long getLastTelefoneID() {
        return lastTelefoneID;
    }

    public void setLastTelefoneID(long lastTelefoneID) {
        this.lastTelefoneID = lastTelefoneID;
    }

    public long getLastPeriodosTrabalhadosID() {
        return lastPeriodosTrabalhadosID;
    }

    public void setLastPeriodosTrabalhadosID(long lastPeriodosTrabalhadosID) {
        this.lastPeriodosTrabalhadosID = lastPeriodosTrabalhadosID;
    }

    public long getLastCalendarioJustificativasID() {
        return lastCalendarioJustificativasID;
    }

    public void setLastCalendarioJustificativasID(long lastCalendarioJustificativasID) {
        this.lastCalendarioJustificativasID = lastCalendarioJustificativasID;
    }

    public long getLastEmpresaID() {
        return lastEmpresaID;
    }

    public void setLastEmpresaID(long lastEmpresaID) {
        this.lastEmpresaID = lastEmpresaID;
    }

    public long getLastJornadaTrabalhoID() {
        return lastJornadaTrabalhoID;
    }

    public void setLastJornadaTrabalhoID(long lastJornadaTrabalhoID) {
        this.lastJornadaTrabalhoID = lastJornadaTrabalhoID;
    }
}
