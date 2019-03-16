package br.fiap;
import br.fiap.Problema.Acao;
import br.fiap.Problema.Estado;

/**
 * Enumera��o que declara todas as poss�veis a��es. 
 * @author antonio
 */

class AcaoRainhas implements Acao {

	int linha, coluna;
	public AcaoRainhas(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	@Override
	public String toString() {
		return "Mover rainha na linha " + getLinha() + " para coluna " + getColuna();
	}
}


/**
 * Classe que define o estado do problema
 * 
 * @author antonio
 *
 */

class EstadoRainhas implements Estado {

	/**
	 * Variáveis de estado
	 * Cada rainha se encontra em uma linha diferente
	 * Preciso guardar apenas suas colunas
	 */
	public int[] rainhas;
	public EstadoRainhas(int r0, int r1, int r2, int r3) {
		this.rainhas = new int[] {r0, r1, r2, r3};
	}	
}

public class Problema4Rainhas implements Problema<EstadoRainhas> {

	@Override
	public Acao[] acoes(EstadoRainhas estadoAtual) {
		Acao[] a = new Acao[16];
		int cont = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				a[cont++] = new AcaoRainhas(i, j);
			}
		}
		return a;
	}


	@Override
	public double custo(EstadoRainhas estadoAtual, br.fiap.Problema.Acao acao) {
		return 1.0;
	}



	@Override
	public EstadoRainhas aplica(Acao a, EstadoRainhas atual) {

		AcaoRainhas acao = (AcaoRainhas) a;
		int r0 = atual.rainhas[0];
		int r1 = atual.rainhas[1];
		int r2 = atual.rainhas[2];
		int r3 = atual.rainhas[3];

		switch(acao.getLinha()) {
		case 0:
			r0 = acao.getColuna();
			break;
		case 1:
			r1 = acao.getColuna();
			break;
		case 2:
			r2 = acao.getColuna();
			break;
		case 3:
			r3 = acao.getColuna();
			break;
		}
		return new EstadoRainhas(r0, r1, r2, r3);
	}


	@Override
	public boolean terminal(EstadoRainhas atual) {
		
		//Para cada rainha, verificamos se n�o h� nenhuma rainha abaixo dela que seja conflitante
		for (int i = 0; i < atual.rainhas.length; i++) {
			//1 - Pela modelagem do problema, certamente n�o h� outra rainha na mesma linha
			//2 - Verifica se h� alguma rainha abaixo na mesma coluna
			for(int j = i+1; j < atual.rainhas.length; j++) {
				if(atual.rainhas[j] == atual.rainhas[i]) {
					return false;
				}
			}
			//2 - Verifica se h� alguma rainha na mesma diagonal abaixo e � direita
			for(int j = i+1; j < atual.rainhas.length; j++) {
				if(atual.rainhas[j] == atual.rainhas[i] + j - i) {
					return false;
				}
			}
			//3 - Verifica se h� alguma rainha na mesma diagonal abaixo e � esquerda
			for(int j = i+1; j < atual.rainhas.length; j++) {
				if(atual.rainhas[j] == atual.rainhas[i] - j + i) {
					return false;
				}
			}
		}

		return true;

	}

}