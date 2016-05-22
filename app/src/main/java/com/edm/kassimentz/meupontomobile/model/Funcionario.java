package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;
import java.util.List;

public class Funcionario implements Serializable {

	private Integer id;
	private String nome;
	private String cpf;
	private String cargo;
	private Empresa empresa;
	private JornadaTrabalho jornadaTrabalho;
	private List<Endereco> enderecos;
	private List<PeriodosTrabalhados> periodosTrabalhados;
	private List<CalendarioJustificativas> calendarioJustificativas;
	private List<Telefone> telefones;

	public Funcionario(){

	}

	public Funcionario(String nome, String cpf, String cargo, Empresa empresa, JornadaTrabalho jornadaTrabalho, List<Endereco> endereco, List<PeriodosTrabalhados> periodosTrabalhados, List<CalendarioJustificativas> calendarioJustificativas, List<Telefone> telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.empresa = empresa;
		this.jornadaTrabalho = jornadaTrabalho;
		this.enderecos = endereco;
		this.periodosTrabalhados = periodosTrabalhados;
		this.calendarioJustificativas = calendarioJustificativas;
		this.telefones = telefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> endereco) {
		this.enderecos = endereco;
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

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefone) {
		this.telefones = telefone;
	}
}
