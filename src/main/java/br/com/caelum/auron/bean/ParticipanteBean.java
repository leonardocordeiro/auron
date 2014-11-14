package br.com.caelum.auron.bean;

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

import br.com.caelum.auron.dao.ParticipanteDao;
import br.com.caelum.auron.modelo.Participante;

@Named
@RequestScoped
public class ParticipanteBean {
	@Inject
	private ParticipanteDao dao;

	@Inject
	private Participante participante;

	private List<Participante> participantes;
	
	@Inject
	private Subject user;
	
	@Inject
	private FacesContext ctx;

	public String cadastra() {
		dao.salva(participante);
		participantes = null;
		
		return "index?faces-redirect=true";
	}

	public String login() throws IOException {
		UsernamePasswordToken token = new UsernamePasswordToken(participante.getEmail(),
																participante.getSenha());
		try {
			user.login(token);
			return "sorteio?faces-redirect=true";
		} catch (AuthenticationException e) {
			ctx.addMessage(null, new FacesMessage("Email ou senha inválido!"));
		}
		return null;
	}
	
	public String logout() { 
		user.logout();
		return "login?faces-redirect=true";
	}

	public List<Participante> getParticipantes() {
		if (participantes == null)
			participantes = dao.getParticipantes();
		return participantes;
	}

	public Participante getParticipante() {
		return participante;
	}
}
