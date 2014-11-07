package br.com.caelum.auron.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.auron.beans.ParticipanteDAO;
import br.com.caelum.auron.model.Participante;

@Named
@RequestScoped
public class ParticipanteBean {
	@Inject 
	private ParticipanteDAO dao;
	
	@Inject
	private Participante participante;
	
	private List<Participante> participantes;
	
	@PostConstruct
	public void init() { 
		this.participantes = dao.getParticipantes();
	}
	
	public void cadastra() { 
		dao.salva(participante);
		participantes = null;
	}
	
	public List<Participante> getParticipantes() { 
		if(participantes == null) 
			participantes = dao.getParticipantes();
		return participantes;
	}
	
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
}
