package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;
import java.util.Date;

public class JornadaTrabalho  implements Serializable {

	private Integer id;
	private Integer duracao_intervalo;
	private Integer tempo_alerta_intervalo;
	private Date hora_inicio_jornada;
	private Date hora_saida_intervalo;
	private Date hora_termino_jornada;
	private Double horas_trabalho_dia;
	private Integer dias_trabalho_semana;
	private Boolean trabalho_domingo;
	private Periodo periodo;

	public JornadaTrabalho(){

	}

    public JornadaTrabalho(Integer duracao_intervalo, Integer tempo_alerta_intervalo, Date hora_inicio_jornada, Date hora_saida_intervalo, Date hora_termino_jornada, Double horas_trabalho_dia, Integer dias_trabalho_semana, Boolean trabalho_domingo, Periodo periodo) {
        this.duracao_intervalo = duracao_intervalo;
        this.tempo_alerta_intervalo = tempo_alerta_intervalo;
        this.hora_inicio_jornada = hora_inicio_jornada;
        this.hora_saida_intervalo = hora_saida_intervalo;
        this.hora_termino_jornada = hora_termino_jornada;
        this.horas_trabalho_dia = horas_trabalho_dia;
        this.dias_trabalho_semana = dias_trabalho_semana;
        this.trabalho_domingo = trabalho_domingo;
        this.periodo = periodo;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDuracao_intervalo() {
		return duracao_intervalo;
	}

	public void setDuracao_intervalo(Integer duracao_intervalo) {
		this.duracao_intervalo = duracao_intervalo;
	}

	public Integer getTempo_alerta_intervalo() {
		return tempo_alerta_intervalo;
	}

	public void setTempo_alerta_intervalo(Integer tempo_alerta_intervalo) {
		this.tempo_alerta_intervalo = tempo_alerta_intervalo;
	}

	public Date getHora_inicio_jornada() {
		return hora_inicio_jornada;
	}

	public void setHora_inicio_jornada(Date hora_inicio_jornada) {
		this.hora_inicio_jornada = hora_inicio_jornada;
	}

	public Date getHora_saida_intervalo() {
		return hora_saida_intervalo;
	}

	public void setHora_saida_intervalo(Date hora_saida_intervalo) {
		this.hora_saida_intervalo = hora_saida_intervalo;
	}

	public Date getHora_termino_jornada() {
		return hora_termino_jornada;
	}

	public void setHora_termino_jornada(Date hora_termino_jornada) {
		this.hora_termino_jornada = hora_termino_jornada;
	}

	public Double getHoras_trabalho_dia() {
		return horas_trabalho_dia;
	}

	public void setHoras_trabalho_dia(Double horas_trabalho_dia) {
		this.horas_trabalho_dia = horas_trabalho_dia;
	}

	public Integer getDias_trabalho_semana() {
		return dias_trabalho_semana;
	}

	public void setDias_trabalho_semana(Integer dias_trabalho_semana) {
		this.dias_trabalho_semana = dias_trabalho_semana;
	}

	public Boolean isTrabalho_domingo() {
		return trabalho_domingo;
	}

	public void setTrabalho_domingo(Boolean trabalho_domingo) {
		this.trabalho_domingo = trabalho_domingo;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
}
