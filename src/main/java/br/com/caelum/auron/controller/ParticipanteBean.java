package br.com.caelum.auron.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

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

	public String cadastra() {
		dao.salva(participante);
		participantes = null;
		
		return "index?faces-redirect=true";
	}

	public String login() throws IOException {
		System.out.println("Teste");
		FacesContext ctx = FacesContext.getCurrentInstance();
		UsernamePasswordToken token = new UsernamePasswordToken(participante.getEmail(), participante.getSenha());
		Subject currentUser = SecurityUtils.getSubject();
		
		try {
			currentUser.login(token);
			return "sorteio?faces-redirect=true";
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário/senha inválido(s)!", "Usuário/senha inválido(s)!"));
		}
		return null;
	}

	public List<Participante> getParticipantes() {
		if (participantes == null)
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
