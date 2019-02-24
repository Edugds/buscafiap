package br.fiap;

public interface Problema<T extends Problema.Estado> {
	/**
	 * Enumera as poss�veis a��es a serem executadas no estado atual
	 * @param estadoAtual
	 * @return as possiveis acoes neste estado
	 */
	public Acao[] acoes(T estadoAtual);
	
	/**
	 * Indica o custo de se aplicar a a��o estando no estado atual
	 * @param estadoAtual
	 * @param acao
	 * @return o custo da a��o
	 */
	public double custo(T estadoAtual, Acao acao);
	
	/**
	 * Indica o estado resultante de se aplicar a a��o ao estado atual
	 * Caso uma a��o n�o possa ser aplicada em determinado estado,
	 * o estado resultante deve ser <code>null</code>
	 * @param acao
	 * @param estadoAtual
	 * @return
	 */
	public T aplica(Acao a, T estadoAtual);
	
	/**
	 * Indica se o estado � terminal ou n�o
	 * @return
	 */
	public boolean terminal(T estadoAtual);
	
	public static interface Estado { };
	
	public static interface Acao { };
	
	
}
