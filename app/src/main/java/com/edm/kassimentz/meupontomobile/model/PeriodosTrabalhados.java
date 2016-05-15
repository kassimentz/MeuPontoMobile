package com.edm.kassimentz.meupontomobile.model;


import java.io.Serializable;
import java.util.Calendar;

public class PeriodosTrabalhados  implements Serializable {

	private Calendar data_hora_inicio;
	private Calendar data_hora_fim;

	public PeriodosTrabalhados(){

	}


    public PeriodosTrabalhados(Calendar data_hora_inicio, Calendar data_hora_fim) {
        this.data_hora_inicio = data_hora_inicio;
        this.data_hora_fim = data_hora_fim;
    }

    public Calendar getData_hora_inicio() {
		return data_hora_inicio;
	}

	public void setData_hora_inicio(Calendar data_hora_inicio) {
		this.data_hora_inicio = data_hora_inicio;
	}

	public Calendar getData_hora_fim() {
		return data_hora_fim;
	}

	public void setData_hora_fim(Calendar data_hora_fim) {
		this.data_hora_fim = data_hora_fim;
	}
}
