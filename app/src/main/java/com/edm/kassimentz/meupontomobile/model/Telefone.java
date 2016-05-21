package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;

public class Telefone  implements Serializable {

	private Integer id;
	private int ddd;
	private int numero;

	public Telefone(){

	}

    public Telefone(int ddd, int numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

	public Integer getId() {
		return id;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
