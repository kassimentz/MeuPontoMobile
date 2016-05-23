package com.edm.kassimentz.meupontomobile.model;

import java.io.Serializable;
import java.util.List;

public class Empresa  implements Serializable {

    private Integer id;
	private String nome;
	private Endereco endereco;
    private List<Telefone> telefones;

    public Empresa(){

    }

    public Empresa(String nome, Endereco endereco, List<Telefone> telefone) {
        this.nome = nome;
        this.endereco = endereco;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones (List<Telefone> telefone) {
        this.telefones = telefone;
    }
}
