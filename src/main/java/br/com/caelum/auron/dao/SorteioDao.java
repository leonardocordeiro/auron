package br.com.caelum.auron.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.auron.modelo.Par;
import br.com.caelum.auron.modelo.Sorteio;

@Stateless
public class SorteioDao {
	@PersistenceContext
	private EntityManager em;
	
	public void inserir(Sorteio sorteio) { 
		em.persist(sorteio);
	}
	
	public List<Sorteio> getSorteios() { 
		return em.createQuery("from Sorteio", Sorteio.class).getResultList();
	}

	public List<Par> getPares() {
		return em.createQuery("from Par", Par.class).getResultList();
	}
}
