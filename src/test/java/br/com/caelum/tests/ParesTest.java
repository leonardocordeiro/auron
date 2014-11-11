package br.com.caelum.tests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.auron.model.Par;
import br.com.caelum.auron.model.Participante;
import br.com.caelum.auron.model.Sorteador;
import br.com.caelum.auron.model.Sorteio;
import br.com.caelum.auron.model.SorteioException;

public class ParesTest {
	private Participante p1;
	private Participante p2;
	private Participante p3;
	
	private List<Participante> participantes;
	private Sorteio sorteio;
	
	@Before
	public void setup() { 
		p1 = new Participante();
		p1.setNome("Leonardo");
		p2 = new Participante();
		p2.setNome("Nico");
		p3 = new Participante();
		p3.setNome("Fábio");
		
		participantes = Arrays.asList(p1, p2, p3);
		sorteio = new Sorteio();	
	}
	
	@Test
	public void naoDeveTerUmMesmoAmigoEmMaisDeUmPar() throws SorteioException { 
		Sorteador sorteador = new Sorteador(participantes);
		sorteador.sortear(sorteio);
		
		Set<Par> pares = sorteio.getPares();
		Iterator<Par> it = pares.iterator();
		
		Par par = it.next();
		Par par2 = it.next();
		Par par3 = it.next();
		
		Participante amigo1 = par.getAmigo();
		Participante amigo2 = par2.getAmigo();
		Participante amigo3 = par3.getAmigo();
	
		assertFalse(amigo1.equals(amigo2));
		assertFalse(amigo2.equals(amigo3));
	}
	
	@Test
	public void naoDeveTerUmMesmoAmigoOcultoEmMaisDeUmPar() throws SorteioException {
		Sorteador sorteador = new Sorteador(participantes);
		sorteador.sortear(sorteio);
		
		Set<Par> pares = sorteio.getPares();
		Iterator<Par> it = pares.iterator();
		
		Par par = it.next();
		Par par2 = it.next();
		Par par3 = it.next();
		
		Participante amigoOculto1 = par.getAmigoOculto();
		Participante amigoOculto2 = par2.getAmigoOculto();
		Participante amigoOculto3 = par3.getAmigoOculto();
	
		assertFalse(amigoOculto1.equals(amigoOculto2));
		assertFalse(amigoOculto2.equals(amigoOculto3));
	
	}
	
	@Test(expected=SorteioException.class)
	public void naoDeveAceitarUmaListaComMenosDeDoisParticipantes() throws SorteioException { 
		Sorteador sorteador = new Sorteador(new ArrayList<Participante>());
		sorteador.sortear(sorteio);
		
	}
	
	@Test
	public void deveVerificarSeAmigoEDiferenteDoAmigoOculto() throws SorteioException { 
		Sorteador sorteador = new Sorteador(participantes);
		sorteador.sortear(sorteio);
		
		Set<Par> pares = sorteio.getPares();
		Iterator<Par> it = pares.iterator();
		
		Par par = it.next();
		Par par2 = it.next();
		Par par3 = it.next();
		
		Participante participante1 = par.getAmigo();
		Participante participante2 = par.getAmigoOculto();
		
		Participante participante3 = par2.getAmigo();
		Participante participante4 = par2.getAmigoOculto();
		
		Participante participante5 = par3.getAmigo();
		Participante participante6 = par3.getAmigoOculto();
		
		boolean resultado = participante1.equals(participante2)
				&& participante3.equals(participante4)
				&& participante5.equals(participante6);
		
		assertFalse(resultado);
	}
	
	@Test
	public void deveVerificarSeOAmigoOcultoDoUltimoParEOAmigoDoPrimeiroPar() throws SorteioException { 
		Sorteador sorteador = new Sorteador(participantes);
		sorteador.sortear(sorteio);
		
		Set<Par> pares = sorteio.getPares();
		Iterator<Par> it = pares.iterator();
		Par par1 = it.next();
		it.next();
		Par par3 = it.next();
		
		Participante primeiro = par1.getAmigo();
		Participante ultimo = par3.getAmigoOculto();

		assertTrue(ultimo.equals(primeiro));
	}
}
