package br.fiap;

import br.fiap.Problema.Acao;

public class ExecutaBusca {

	public static void main(String[] args) {
		
		//Busca<EstadoAspirador> busca = new Busca<EstadoAspirador>(new ProblemaAspirador()); 
		EstadoRainhas inicial = new EstadoRainhas(0, 0, 0, 0);
		Problema4Rainhas problema = new Problema4Rainhas();
		Busca<EstadoRainhas> busca = new Busca<EstadoRainhas>(problema); 
		busca.setEstadoInicial(inicial);
		busca.resolver();
		
		if(busca.getSolucao() == null) {
			System.out.println("Nao ha solucao para o problema");
		} else {
			EstadoRainhas proximo = inicial;
			System.out.println("A solucao: ");
			System.out.println(proximo);
			for(Acao a : busca.getSolucao()) {
				proximo = problema.aplica(a, proximo);
				System.out.println(a);
				System.out.println(proximo);
			}
		}

	}

}
