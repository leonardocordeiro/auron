package br.com.caelum.auron.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	public List<Par> getPares(String email) {
		TypedQuery<Par> query = em.createQuery("from Par p where p.amigo.email = ?", Par.class);
		query.setParameter(1, email);
		
		return query.getResultList();
	}
}
