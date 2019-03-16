package br.fiap;

import br.fiap.Problema.Acao;

public class ExecutaBusca {

	public static void main(String[] args) {
		
		//Busca<EstadoAspirador> busca = new Busca<EstadoAspirador>(new ProblemaAspirador()); 
		Busca<EstadoRainhas> busca = new Busca<EstadoRainhas>(new Problema4Rainhas()); 
		busca.setEstadoInicial(new EstadoRainhas(0, 0, 0, 0));
		busca.resolver();
		
		if(busca.getSolucao() == null) {
			System.out.println("Nao ha solucao para o problema");
		} else {
			System.out.println("A solucao: ");
			for(Acao a : busca.getSolucao()) {
				System.out.println(a);
			}
		}

	}

}
