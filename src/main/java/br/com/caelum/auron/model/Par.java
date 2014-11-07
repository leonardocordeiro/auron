package br.com.caelum.auron.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Par {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Participante amigo;
	@ManyToOne
	private Participante amigoOculto;
	@ManyToOne
	private Sorteio sorteio;
	
	public Par(Participante participante, Participante participante2, Sorteio sorteio) { 
		this.amigo = participante;
		this.amigoOculto = participante2;
		this.sorteio = sorteio;
	}
	
	Par() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Participante getAmigo() {
		return amigo;
	}

	public void setAmigo(Participante participante1) {
		this.amigo = participante1;
	}

	public Participante getAmigoOculto() {
		return amigoOculto;
	}

	public void setAmigoOculto(Participante participante2) {
		this.amigoOculto = participante2;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}
	
}
