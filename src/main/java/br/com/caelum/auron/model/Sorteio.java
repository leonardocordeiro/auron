package br.com.caelum.auron.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sorteio {
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="sorteio")
	private Set<Par> pares = new LinkedHashSet<Par>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Par> getPares() {
		return Collections.unmodifiableSet(pares);
	}

	public void adicionaPares(Par par) {
		this.pares.add(par);
	}
}
