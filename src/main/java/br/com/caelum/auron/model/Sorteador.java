package br.com.caelum.auron.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorteador {
	private final List<Participante> participantes;
	private final int totalDeParticipantes;

	public Sorteador(List<Participante> participantes) {
		this.participantes = new ArrayList<>(participantes);
		totalDeParticipantes = participantes.size();
	}

	public void sortear(Sorteio sorteio) throws SorteioException {
		verificaTamanhoDaListaDeParticipantes();
		embaralhaParticipantes();

		int atual = 0;
		while (atual < totalDeParticipantes) {
			if (participanteAtualEHOUltimo(atual)) {
				criaParEAdicionaNoSorteio(sorteio, atual, 0);
				break;
			}
			criaParEAdicionaNoSorteio(sorteio, atual, atual + 1);
			atual++;
		}
	}

	private boolean participanteAtualEHOUltimo(int atual) {
		return atual == totalDeParticipantes - 1;
	}

	private void criaParEAdicionaNoSorteio(Sorteio sorteio, int atual,
			int proximo) {
		Par par = new Par(participantes.get(atual), participantes.get(proximo),
				sorteio);
		sorteio.adicionaPares(par);
	}

	private void embaralhaParticipantes() {
		Collections.shuffle(participantes);
	}

	private void verificaTamanhoDaListaDeParticipantes() throws SorteioException {
		if (totalDeParticipantes < 2)
			throw new SorteioException("Lista vazia");
	}
}
