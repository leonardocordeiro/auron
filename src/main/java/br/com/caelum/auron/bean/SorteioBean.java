package br.com.caelum.auron.bean;

import java.util.List;
import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.auron.dao.ParticipanteDao;
import br.com.caelum.auron.dao.SorteioDao;
import br.com.caelum.auron.modelo.Par;
import br.com.caelum.auron.modelo.Participante;
import br.com.caelum.auron.modelo.Sorteador;
import br.com.caelum.auron.modelo.Sorteio;
import br.com.caelum.auron.modelo.SorteioException;

@Named
@RequestScoped
public class SorteioBean {
	@Inject
	private ParticipanteDao participanteDao;
	@Inject
	private SorteioDao sorteioDao;
	private Sorteio sorteio = new Sorteio();
	private List<Sorteio> sorteios;
	private List<Par> pares;

	public String sortear() throws SorteioException, NamingException {
		List<Participante> participantes = participanteDao.getParticipantes();
		Sorteador sorteador = new Sorteador(participantes);
		try { 
			sorteador.sortear(sorteio);
			sorteioDao.inserir(sorteio);
		} catch(SorteioException e) { 
			FacesContext.getCurrentInstance().addMessage("lista.vazia", new FacesMessage("Nao existem participantes para se fazer um sorteio!"));
		}
		return "sorteio?faces-redirect=true";
	}

	public List<Par> getPares() { 
		if(pares == null)
			pares = sorteioDao.getPares();
		return pares;
	}
	
	public List<Sorteio> getSorteios() {
		if (sorteios == null)
			sorteios = sorteioDao.getSorteios();
		return sorteios;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}
}
