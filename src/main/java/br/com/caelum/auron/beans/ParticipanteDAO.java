package br.com.caelum.auron.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.caelum.auron.model.Participante;

@Stateless
public class ParticipanteDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void salva(Participante participante) {
		em.persist(participante);
	}
	
	public List<Participante> getParticipantes() { 
		TypedQuery<Participante> query = em.createQuery("from Participante", Participante.class);
		return query.getResultList();
	}
}
