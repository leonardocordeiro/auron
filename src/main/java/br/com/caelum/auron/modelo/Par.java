package br.com.caelum.auron.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Par {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Participante amigo;
	@ManyToOne
	private Participante amigoOculto;
	@ManyToOne
	private Sorteio sorteio;
	
	public Par(Participante amigo, Participante amigoOculto, Sorteio sorteio) { 
		this.amigo = amigo;
		this.amigoOculto = amigoOculto;
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

	public void setAmigo(Participante amigo) {
		this.amigo = amigo;
	}

	public Participante getAmigoOculto() {
		return amigoOculto;
	}

	public void setAmigoOculto(Participante amigoOculto) {
		this.amigoOculto = amigoOculto;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}
}
