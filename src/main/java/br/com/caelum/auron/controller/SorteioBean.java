package br.com.caelum.auron.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.auron.beans.ParticipanteDAO;
import br.com.caelum.auron.beans.SorteioDAO;
import br.com.caelum.auron.model.Par;
import br.com.caelum.auron.model.Participante;
import br.com.caelum.auron.model.Sorteador;
import br.com.caelum.auron.model.Sorteio;

@Named
@RequestScoped
public class SorteioBean {
	@Inject
	private ParticipanteDAO participanteDao;
	@Inject
	private SorteioDAO sorteioDao;
	private Sorteio sorteio = new Sorteio();

	private List<Sorteio> sorteios;
	private List<Par> pares;

	public void sortear() {
		List<Participante> participantes = participanteDao.getParticipantes();
		Sorteador sorteador = new Sorteador(participantes);
		try { 
			sorteador.sortear(sorteio);
			sorteioDao.inserir(sorteio);
		} catch(RuntimeException e) { 
			FacesContext.getCurrentInstance().addMessage("lista.vazia", new FacesMessage("Nao existem participantes para se fazer um sorteio!"));
		}
	}

	public List<Par> getPares() { 
		if(pares == null)
			return sorteioDao.getPares();
		return pares;
	}
	
	public List<Sorteio> getSorteios() {
		if (sorteios == null)
			return sorteioDao.getSorteios();
		return sorteios;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}
}
