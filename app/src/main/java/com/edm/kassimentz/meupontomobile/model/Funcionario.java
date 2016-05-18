package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;
import java.util.List;

public class Funcionario implements Serializable {

	private Integer id_funcionario;
	private String nome;
	private String cpf;
	private String cargo;
	private Empresa empresa;
	private JornadaTrabalho jornadaTrabalho;
	private List<Endereco> endereco;
	private List<PeriodosTrabalhados> periodosTrabalhados;
	private List<CalendarioJustificativas> calendarioJustificativas;
	private List<Telefone> telefone;

	public Funcionario(){

	}

	public Funcionario(String nome, String cpf, String cargo, Empresa empresa, JornadaTrabalho jornadaTrabalho, List<Endereco> endereco, List<PeriodosTrabalhados> periodosTrabalhados, List<CalendarioJustificativas> calendarioJustificativas, List<Telefone> telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.empresa = empresa;
		this.jornadaTrabalho = jornadaTrabalho;
		this.endereco = endereco;
		this.periodosTrabalhados = periodosTrabalhados;
		this.calendarioJustificativas = calendarioJustificativas;
		this.telefone = telefone;
	}

	public Integer getId() {
		return id_funcionario;
	}

	public void setId(Integer id) {
		this.id_funcionario = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public JornadaTrabalho getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public void setJornadaTrabalho(JornadaTrabalho jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<PeriodosTrabalhados> getPeriodosTrabalhados() {
		return periodosTrabalhados;
	}

	public void setPeriodosTrabalhados(List<PeriodosTrabalhados> periodosTrabalhados) {
		this.periodosTrabalhados = periodosTrabalhados;
	}

	public List<CalendarioJustificativas> getCalendarioJustificativas() {
		return calendarioJustificativas;
	}

	public void setCalendarioJustificativas(List<CalendarioJustificativas> calendarioJustificativas) {
		this.calendarioJustificativas = calendarioJustificativas;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
}
