package br.com.caelum.auron.modelo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Sorteio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message="{sorteio.nome.vazio}")
	private String nome;
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="sorteio")
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
