package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;
import java.util.List;

public class Empresa  implements Serializable {

    private Integer id;
	private String nome;
	private Endereco endereco;
    private List<Telefone> telefone;

    public Empresa(){

    }

    public Empresa(String nome, Endereco endereco, List<Telefone> telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }
}
