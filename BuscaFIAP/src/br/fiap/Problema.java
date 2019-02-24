package br.fiap;

public interface Problema<T extends Problema.Estado> {
	/**
	 * Enumera as possíveis ações a serem executadas no estado atual
	 * @param estadoAtual
	 * @return as possiveis acoes neste estado
	 */
	public Acao[] acoes(T estadoAtual);
	
	/**
	 * Indica o custo de se aplicar a ação estando no estado atual
	 * @param estadoAtual
	 * @param acao
	 * @return o custo da ação
	 */
	public double custo(T estadoAtual, Acao acao);
	
	/**
	 * Indica o estado resultante de se aplicar a ação ao estado atual
	 * Caso uma ação não possa ser aplicada em determinado estado,
	 * o estado resultante deve ser <code>null</code>
	 * @param acao
	 * @param estadoAtual
	 * @return
	 */
	public T aplica(Acao a, T estadoAtual);
	
	/**
	 * Indica se o estado é terminal ou não
	 * @return
	 */
	public boolean terminal(T estadoAtual);
	
	public static interface Estado { };
	
	public static interface Acao { };
	
	
}
