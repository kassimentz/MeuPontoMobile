package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;
import java.util.Calendar;

public class CalendarioJustificativas implements Serializable{

    private Integer id;
	private Calendar data_hora;
	private String observacao;
	private Justificativa justificativa;

    public CalendarioJustificativas(){

    }

    public CalendarioJustificativas(Calendar data_hora, String observacao, Justificativa justificativa) {
        this.data_hora = data_hora;
        this.observacao = observacao;
        this.justificativa = justificativa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData_hora() {
        return data_hora;
    }

    public void setData_hora(Calendar data_hora) {
        this.data_hora = data_hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Justificativa getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(Justificativa justificativa) {
        this.justificativa = justificativa;
    }
}
