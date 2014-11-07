package br.com.caelum.auron.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.resource.spi.IllegalStateException;

public class Sorteador {
	private List<Participante> participantes;
	
	public Sorteador(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public void sortear(Sorteio sorteio) {
		embaralhaParticipantes();
		
		int atual = 0;
		while(atual < participantes.size()) {
			if(seParticipanteAtualForOUltimo(atual)) {
				criaParEAdicionaNoSorteio(sorteio, atual, 0);
				break;
			}
			criaParEAdicionaNoSorteio(sorteio, atual, atual + 1);
			atual++;
		}
	}

	private boolean seParticipanteAtualForOUltimo(int atual) {
		return atual == participantes.size() - 1;
	}

	private void criaParEAdicionaNoSorteio(Sorteio sorteio, int atual, int proximo) {
		Par par = new Par(participantes.get(atual), participantes.get(proximo), sorteio);
		sorteio.adicionaPares(par);
	}

	private void embaralhaParticipantes() {
		if(participantes.size() < 2)
			throw new RuntimeException("Lista vazia");
			Collections.shuffle(participantes);
	}
}

//		Collections.shuffle(participantes);
//		Set<Par> pares = new HashSet<Par>();
//
//		int atual = 0;
//		while(atual <= participantes.size() - 1) {
//			if(atual == participantes.size() - 1) {
//				Par par = new Par(participantes.get(atual), participantes.get(0));
//				pares.add(par);
//				return pares;
//			}
//			Par par = new Par(participantes.get(atual), participantes.get(atual + 1));
//			pares.add(par);
//			atual++;
//		}
//		
//		return pares;