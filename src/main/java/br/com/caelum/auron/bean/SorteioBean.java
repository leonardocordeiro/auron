package br.com.caelum.auron.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import org.apache.shiro.subject.Subject;

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
	@Inject
	private Subject user;
	@Inject
	private FacesContext ctx;
	
	
	public String sortear() throws SorteioException, NamingException {
		List<Participante> participantes = participanteDao.getParticipantes();
		Sorteador sorteador = new Sorteador(participantes);
		try { 
			sorteador.sortear(sorteio);
			sorteioDao.inserir(sorteio);
		} catch(SorteioException e) { 
			ctx.addMessage("lista.vazia", new FacesMessage("Nao existem participantes para se fazer um sorteio!"));
		}
		return "sorteio?faces-redirect=true";
	}

	public List<Par> getPares() { 
		String email = (String) user.getPrincipal();
		
		if(pares == null)
			pares = sorteioDao.getPares(email);
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
