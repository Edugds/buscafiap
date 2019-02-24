package br.fiap;

import java.util.Deque;
import java.util.LinkedList;

import br.fiap.Problema.Acao;
import br.fiap.Problema.Estado;


/**
 * Estrutura contendo o n� de busca, como 
 * explicado em sala de aula.
 * O custo � calculado diretamente a partir da a��o e do estado.
 * 
 * @author antonio
 *
 */
class No<T extends Estado> {
	public final T estado;
	public final No<T> pai;
	public final Acao acao;
	public final double custo;
	
	public No(T e, No<T> pai, Acao a, Problema<T> p) {
		this.estado = e;
		this.pai = pai;
		this.acao = a;
		this.custo = pai == null ? 0 : pai.custo + p.custo(pai.estado, a);
	}
}

/**
 * Classe que implementa a lista de fronteira.
 * è ela quem define a estratégia de busca.
 * @author antonio
 *
 */
class Fronteira<T extends Estado> {
	
	private Deque<No<T>> lista = new LinkedList<No<T>>();

	/**
	 * @return Se a fronteira está vazia
	 */
	public boolean vazio() {
		return lista.isEmpty();
	}

	/**
	 * Insere o nó na lista de fronteira
	 * @param no 
	 */
	public void insere(No<T> no) {
		lista.addLast(no);
	}

	/**
	 * Retira o próximo nó de acordo com a política
	 * da lista de fronteira
	 * 
	 * @return O proximo nó da lista
	 */
	public No<T> retiraProximo() {
		//return lista.pollLast(); //LIFO
		return lista.pollFirst(); //FIFO
	}
}

/**
 * Classe que implementa o algoritmo de busca
 * @author antonio
 *
 */
public class Busca<T extends Estado> {

	private final Problema<T> problema;
	
	private T estadoInicial;
	private Fronteira<T> fronteira = new Fronteira<T>();
	
	private Acao[] solucao = null;
	private double custoSolucao = 0;
	
	public Busca(Problema<T> problema) {
		this.problema = problema;
	}
	
	public Acao[] getSolucao() {
		return solucao;
	}
	
	public double getCustoSolucao() {
		return custoSolucao;
	}
	
	public void setEstadoInicial(T inicial) {
		estadoInicial = inicial;
	}			

	private No<T> buscar() {
		
		No<T> aberto = new No<T>(estadoInicial, null, null, problema);
		fronteira.insere(aberto);
		
		while(!fronteira.vazio()) {
			aberto = fronteira.retiraProximo();
			
			if(problema.terminal(aberto.estado)) {
				return aberto;
			} else for(Acao acao : problema.acoes(aberto.estado)) {
				T vizinho = problema.aplica(acao, aberto.estado);
				if(vizinho != null) {
					No<T> filho = new No<T>(vizinho,aberto,acao,problema);
					fronteira.insere(filho);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Resolve o problema com base em busca, realizando
	 * o backtracking após chegar ao estado meta
	 * 
	 * @return O custo da solução encontrada
	 */
	public double resolver() {
		
		No<T> destino = buscar();
		if(destino == null) {
			this.solucao = null;
			this.custoSolucao = 0;
			return 0;
		}
		
		No<T> atual = destino;
		Deque<Acao> caminho = new LinkedList<Acao>();
		while(atual.acao != null) {
			caminho.addFirst(atual.acao);
			atual = atual.pai;
		}
		
		this.solucao = caminho.toArray(new Acao[caminho.size()]);
		this.custoSolucao = destino.custo;
		return this.custoSolucao;
	}
}
