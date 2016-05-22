package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;
import java.util.Calendar;

public class JornadaTrabalho  implements Serializable {

	private int id;
	private int duracao_intervalo;
	private int tempo_alerta_intervalo;
	private Calendar hora_inicio_jornada;
	private Calendar hora_saida_intervalo;
	private Calendar hora_termino_jornada;
	private Double horas_trabalho_dia;
	private int dias_trabalho_semana;
	private boolean trabalho_domingo;
	private Periodo periodo;

	public JornadaTrabalho(){

	}

    public JornadaTrabalho(int duracao_intervalo, int tempo_alerta_intervalo, Calendar hora_inicio_jornada, Calendar hora_saida_intervalo, Calendar hora_termino_jornada, Double horas_trabalho_dia, int dias_trabalho_semana, boolean trabalho_domingo, Periodo periodo) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuracao_intervalo() {
		return duracao_intervalo;
	}

	public void setDuracao_intervalo(int duracao_intervalo) {
		this.duracao_intervalo = duracao_intervalo;
	}

	public int getTempo_alerta_intervalo() {
		return tempo_alerta_intervalo;
	}

	public void setTempo_alerta_intervalo(int tempo_alerta_intervalo) {
		this.tempo_alerta_intervalo = tempo_alerta_intervalo;
	}

	public Calendar getHora_inicio_jornada() {
		return hora_inicio_jornada;
	}

	public void setHora_inicio_jornada(Calendar hora_inicio_jornada) {
		this.hora_inicio_jornada = hora_inicio_jornada;
	}

	public Calendar getHora_saida_intervalo() {
		return hora_saida_intervalo;
	}

	public void setHora_saida_intervalo(Calendar hora_saida_intervalo) {
		this.hora_saida_intervalo = hora_saida_intervalo;
	}

	public Calendar getHora_termino_jornada() {
		return hora_termino_jornada;
	}

	public void setHora_termino_jornada(Calendar hora_termino_jornada) {
		this.hora_termino_jornada = hora_termino_jornada;
	}

	public Double getHoras_trabalho_dia() {
		return horas_trabalho_dia;
	}

	public void setHoras_trabalho_dia(Double horas_trabalho_dia) {
		this.horas_trabalho_dia = horas_trabalho_dia;
	}

	public int getDias_trabalho_semana() {
		return dias_trabalho_semana;
	}

	public void setDias_trabalho_semana(int dias_trabalho_semana) {
		this.dias_trabalho_semana = dias_trabalho_semana;
	}

	public boolean isTrabalho_domingo() {
		return trabalho_domingo;
	}

	public void setTrabalho_domingo(boolean trabalho_domingo) {
		this.trabalho_domingo = trabalho_domingo;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
}
