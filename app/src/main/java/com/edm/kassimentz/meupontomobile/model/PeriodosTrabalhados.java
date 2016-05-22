package com.edm.kassimentz.meupontomobile.model;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class PeriodosTrabalhados implements Serializable {

	private Integer id;
	private Date data_hora_inicio;
	private Date data_hora_fim;

	public PeriodosTrabalhados(){

	}

	public PeriodosTrabalhados(Date data_hora_inicio, Date data_hora_fim) {
        this.data_hora_inicio = data_hora_inicio;
        this.data_hora_fim = data_hora_fim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData_hora_inicio() {
		return data_hora_inicio;
	}

	public void setData_hora_inicio(Date data_hora_inicio) {
		this.data_hora_inicio = data_hora_inicio;
	}

	public Date getData_hora_fim() {
		return data_hora_fim;
	}

	public void setData_hora_fim(Date data_hora_fim) {
		this.data_hora_fim = data_hora_fim;
	}

    @Override
    public String toString() {
        return "PeriodosTrabalhados{" +
                "id=" + id +
                ", data_hora_inicio=" + data_hora_inicio +
                ", data_hora_fim=" + data_hora_fim +
                '}';
    }
}
