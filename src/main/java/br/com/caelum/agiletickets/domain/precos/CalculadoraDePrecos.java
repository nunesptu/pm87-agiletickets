package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		Integer ingressosReservados = sessao.getIngressosReservados();
		Integer totalIngressos = sessao.getTotalIngressos();
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();
		BigDecimal preco = sessao.getPreco();
		
		if(tipo.equals(TipoDeEspetaculo.CINEMA) || tipo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if((totalIngressos - ingressosReservados) / totalIngressos.doubleValue() <= 0.05) { 
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else {
			Integer duracaoEmMinutos = sessao.getDuracaoEmMinutos();
			if(tipo.equals(TipoDeEspetaculo.BALLET) || tipo.equals(TipoDeEspetaculo.ORQUESTRA)) {
				if((totalIngressos - ingressosReservados) / totalIngressos.doubleValue() <= 0.50) { 
					preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
				}
				
				if(duracaoEmMinutos > 60){
					preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
				}
			} 
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}