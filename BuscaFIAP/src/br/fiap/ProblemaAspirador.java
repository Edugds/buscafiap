package br.fiap;

import br.fiap.Problema.Acao;
import br.fiap.Problema.Estado;

/**
 * Enumeração que declara todas as possíveis ações. 
 * @author antonio
 */
enum AcaoAspirador implements Acao {
	DIREITA,
	ESQUERDA,
	ASPIRAR
	;
}

/**
 * Classe que define o estado do problema
 * 
 * @author antonio
 *
 */
class EstadoAspirador implements Estado {

	/**
	 * VariÃ¡veis de estado
	 */
	public final boolean Asujo, Bsujo, quartoA;
	
	public EstadoAspirador(boolean Asujo, boolean Bsujo, boolean quartoA) {
		this.Asujo = Asujo;
		this.Bsujo = Bsujo;
		this.quartoA = quartoA;
	}	
}

public class ProblemaAspirador implements Problema<EstadoAspirador> {

	@Override
	public Acao[] acoes(EstadoAspirador estadoAtual) {
		return AcaoAspirador.values();
	}

	@Override
	public double custo(EstadoAspirador estadoAtual, br.fiap.Problema.Acao acao) {
		return 1.0;
	}

	@Override
	public EstadoAspirador aplica(Acao a, EstadoAspirador atual) {
		if(a == AcaoAspirador.DIREITA) {
			return new EstadoAspirador(atual.Asujo, atual.Bsujo, false /*quartoA*/);
		} else if(a == AcaoAspirador.ESQUERDA) {
			return new EstadoAspirador(atual.Asujo, atual.Bsujo, true /*quartoA*/);
		} else { //acao == AcaoAspirador.ASPIRAR
			if(atual.quartoA) {
				return new EstadoAspirador(false, atual.Bsujo, atual.quartoA);
			} else {
				return new EstadoAspirador(atual.Asujo, false, atual.quartoA);
			}
		}
	}

	@Override
	public boolean terminal(EstadoAspirador atual) {
		return !atual.Asujo && !atual.Bsujo;
	}
	
}