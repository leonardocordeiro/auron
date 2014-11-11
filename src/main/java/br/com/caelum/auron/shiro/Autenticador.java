package br.com.caelum.auron.shiro;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import br.com.caelum.auron.beans.ParticipanteDAO;
import br.com.caelum.auron.model.Participante;

public class Autenticador implements Realm {
	
	private ParticipanteDAO participanteDao;
	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernameToken = (UsernamePasswordToken) token;
		
		String email = usernameToken.getUsername();
		
		String senha = new String(usernameToken.getPassword());

		try {
			participanteDao = getParticipanteDAO();
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("===== " + participanteDao + " ========");
		Participante participante = participanteDao.getParticipante(email, senha);
		
		if(participante != null) {
			AuthenticationInfo info = new SimpleAuthenticationInfo(email, senha, getName());
			/*
			SimpleCredentialsMatcher matcher = new SimpleCredentialsMatcher();
			
			if(matcher.doCredentialsMatch(token, info)) {
	             return info;
	        }
	        */
			return info;
		}
		throw new AuthenticationException();
	}
	
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public ParticipanteDAO getParticipanteDAO() throws NamingException {
		Properties props = new Properties();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(InitialContext.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		props.put(InitialContext.PROVIDER_URL, "remote://localhost:4447");
		props.put(InitialContext.SECURITY_PRINCIPAL, "leonardo");
		props.put(InitialContext.SECURITY_CREDENTIALS, "leo941231");
		
		InitialContext ctx = new InitialContext(props);
		return (ParticipanteDAO) ctx.lookup("ejb:auron/ParticipanteDAO");
	}
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return true;
	}

}
