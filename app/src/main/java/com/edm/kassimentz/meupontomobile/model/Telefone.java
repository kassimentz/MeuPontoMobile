package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;

public class Telefone  implements Serializable {

	private static final long serialVersionUID = -2163051469151704594L;

	private Integer id;
	private String ddd;
	private String numero;

	public Telefone(){

	}

    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
