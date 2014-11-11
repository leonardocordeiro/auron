package br.com.caelum.auron.utils;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ProducesUtils {
	@Produces
	public FacesContext newFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	@Produces
	public Subject newSubject() { 
		return SecurityUtils.getSubject();
	}
}
